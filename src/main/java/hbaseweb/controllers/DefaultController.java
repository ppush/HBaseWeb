/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbaseweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;

/**
 *
 * @author vahan
 */
@Controller
public class DefaultController {

    private byte[] lastrow = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)

    public ModelAndView index(@RequestParam(value = "tablename", required = false) String tablename, @RequestParam(value = "page", required = false) String page, ModelMap map) {

        String test = "valod";
        map.put("test", test);
        map.put("testBytes", Bytes.toBytes(test));

        map.put("tablename", tablename);
        map.put("page", page);
        int ipage = 1;
        try {
            ipage = Integer.parseInt(page);
        } catch (Exception e) {
            ipage = 1;
        }

        try {

            Configuration config = HBaseConfiguration.create();
            config.clear();
            config.set("hbase.zookeeper.quorum", "192.168.10.50");
            config.set("hbase.zookeeper.property.clientPort", "2181");

            HBaseAdmin.checkHBaseAvailable(config);
            System.out.println("HBase is running!");
            Connection connection = ConnectionFactory.createConnection(config);
            Admin admin = connection.getAdmin();

            HTableDescriptor[] tables = admin.listTables();
            map.put("tables", tables);
            if (tablename != "" & tablename != null) {
                TableName tableName = TableName.valueOf(tablename);
                if (admin.tableExists(tableName)) {
//                    HTable table = new HTable(config, tablename);
                    Table table = connection.getTable(tableName);
//                    aa =  table.getTableDescriptor().getFamilies();
                    System.out.println(tablename);

                    byte[] POSTFIX = new byte[]{0x00};
                    Filter filter = new PageFilter(10);
                    int totalRows = 0;

                    lastrow = null;

                    Scan scan = new Scan();
                    scan.setFilter(filter);
                    for (int i = 1; i <= ipage; i++) {
                        if (lastrow != null) {
                            byte[] startRow = Bytes.add(lastrow, POSTFIX);
                            System.out.println("start row: "
                                    + Bytes.toStringBinary(startRow));
                            scan.setStartRow(startRow);
                        }
//                    scan.setStopRow(Bytes.toBytes(2));
                        ResultScanner results2 = table.getScanner(scan);
                        for (Result result = results2.next(); result != null; result = results2.next()) {
                            lastrow = result.getRow();
                            totalRows++;
                        }
                    }
                    ResultScanner results = table.getScanner(scan);

                    Result[] values = results.next(50);

                    results.close();
                    table.close();

                    map.put("values", values);
                }
            }

        } catch (Exception ce) {
            ce.printStackTrace();

        }

//        map.put("msg", "Hello Spring 4 Web MVC!");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
//        return "index";
    }

}
