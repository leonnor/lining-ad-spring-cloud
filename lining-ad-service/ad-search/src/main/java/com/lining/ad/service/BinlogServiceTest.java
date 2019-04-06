package com.lining.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

/**
 * className BinlogServiceTest
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/3 19:55
 */
public class BinlogServiceTest {

    public static void main(String[] args) throws Exception {

        BinaryLogClient client = new BinaryLogClient(
                "127.0.0.1",
                3306,
                "root",
                "root"
        );
        client.registerEventListener(event -> {

            EventData data = event.getData();

            if (data instanceof UpdateRowsEventData){
                System.out.println("Update----------------");
                System.out.println(data.toString());
            } else if (data instanceof WriteRowsEventData){
                System.out.println("Write----------------");
                System.out.println(data.toString());
            } else if (data instanceof DeleteRowsEventData){
                System.out.println("Delete----------------");
                System.out.println(data.toString());
            }
        });

        client.connect();
    }
}
