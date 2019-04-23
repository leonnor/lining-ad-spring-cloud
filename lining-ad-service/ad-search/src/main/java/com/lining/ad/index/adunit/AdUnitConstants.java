package com.lining.ad.index.adunit;

/**
 * className AdUnitConstants
 * description 推广单元的常量类
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/23 13:52
 */
public class AdUnitConstants {

    /**
     * 流量类型
     * 需同步给媒体方
     * 这里的编码使用二进制编排，这样做的好处是可以使用位运算来加快检索的速度
     */
    public static class POSITION_TYPE {

        public static final int KAIPING = 1;
        public static final int TIEPIAN = 2;
        public static final int TIEPIAN_MIDDLE = 4;
        public static final int TIEPIAN_PAUSE = 8;
        public static final int TIEPIAN_POST = 16;
    }
}
