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
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

/**
 *
 * @author vahan
 */
@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)

    public ModelAndView index(@RequestParam(value = "tablename", required = false) String tablename, ModelMap map) {

        String test = "valod";
        map.put("test", test);
        map.put("testBytes", Bytes.toBytes(test));
        
        map.put("tablename", tablename);
        try {

            Configuration config = HBaseConfiguration.create();
            config.clear();
            config.set("hbase.zookeeper.quorum", "192.168.230.11");
            config.set("hbase.zookeeper.property.clientPort", "2181");

            HBaseAdmin.checkHBaseAvailable(config);
            System.out.println("HBase is running!");
            HBaseAdmin admin = new HBaseAdmin(config);

            HTableDescriptor[] tableDescriptor = admin.listTables();
            map.put("tables", tableDescriptor);
            if (tablename != "") {
                if (admin.tableExists(tablename)) {
                    HTable table = new HTable(config, tablename);
                    System.out.println(tablename);
                    Scan scan = new Scan();
                    ResultScanner results = table.getScanner(scan);
                    
                    Result[] values = results.next(50);
//                    for (Result result = results.next(); result != null; result = results.next()) {
//                        
//                        System.out.println("Found row : " + result);
//                    }
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
