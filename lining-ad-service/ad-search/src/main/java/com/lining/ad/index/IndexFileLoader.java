package com.lining.ad.index;

import com.alibaba.fastjson.JSON;
import com.lining.ad.dump.DConstant;
import com.lining.ad.dump.table.*;
import com.lining.ad.handler.AdLevelDataHandler;
import com.lining.ad.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * className IndexFileLoader
 * description 加载全量索引
 * 根据数据表导出的文件读取文件加载索引
 * @author ln
 * @version 1.0
 * @date 2019/4/2 17:36
 */
@Component
@DependsOn("dataTable")
public class IndexFileLoader {

    /**
     * 实现全量索引的加载
     * 在检索系统启动时应该完成加载
     */
    @PostConstruct
    public void init(){
        /** 第二层级（推广计划）全量索引加载*/
        List<String> adPlanStrings = loadDumpData(
                String.format("%s%s",
                    DConstant.DATA_ROOT_DIR,
                    DConstant.AD_PLAN)
        );
        adPlanStrings.forEach(p -> AdLevelDataHandler.handleLevel2(
                /** 反序列化*/
                JSON.parseObject(p, AdPlanTable.class),
                OpType.ADD
        ));

        /** 第二层级（创意）全量索引加载*/
        List<String> adCreativeStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_PLAN)
        );
        adCreativeStrings.forEach(c -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(c, AdCreativeTable.class),
                OpType.ADD
        ));
        /** 第三层级（推广单元）全量索引加载*/
        List<String> adUnitStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT)
        );
        adUnitStrings.forEach(u -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(u, AdUnitTable.class),
                OpType.ADD
        ));
        /** 第三层级（创意与推广单元关联关系）全量索引加载*/
        List<String> adCreativeUnitStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE_UNIT)
        );
        adCreativeUnitStrings.forEach(cu -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(cu, AdCreativeUnitTable.class),
                OpType.ADD
        ));
        /** 第四层级（地域限制）全量索引加载*/
        List<String> adUnitDistrictStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_DISTRICT)
        );
        adUnitDistrictStrings.forEach(d -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(d, AdUnitDistrictTable.class),
                OpType.ADD
        ));
        /** 第四层级（兴趣限制）全量索引加载*/
        List<String> adUnitItStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_IT)
        );
        adUnitItStrings.forEach(i -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(i, AdUnitItTable.class),
                OpType.ADD
        ));
        /** 第四层级（关键词限制）全量索引加载*/
        List<String> adUnitKeywordStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_KEYWORD)
        );
        adUnitKeywordStrings.forEach(k -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(k, AdUnitKeywordTable.class),
                OpType.ADD
        ));
    }

    /**
     * 读取文件中的数据
     * @param fileName
     * @return
     */
    private List<String> loadDumpData(String fileName){

        try (BufferedReader br = Files.newBufferedReader(
                Paths.get(fileName)
        )){
            return br.lines().collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
