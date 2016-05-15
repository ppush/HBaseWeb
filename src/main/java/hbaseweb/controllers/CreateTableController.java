/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbaseweb.controllers;

import hbaseweb.springform.form.NewTableForm;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vahan
 */
@Controller
public class CreateTableController {

    @RequestMapping(value = "/createtable", method = RequestMethod.GET)
    public String index(ModelMap map) {
        NewTableForm newTableForm = new NewTableForm();
        map.put("newTableForm", newTableForm);
//        map.put("msg", "Hello Spring 4 Web MVC page2!");
        return "CreateTable";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    public String newtable(NewTableForm newTableForm,ModelMap map) {

        try {

            Configuration config = HBaseConfiguration.create();
            config.clear();
            config.set("hbase.zookeeper.quorum", "192.168.10.50");
            config.set("hbase.zookeeper.property.clientPort", "2181");
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Admin admin = connection.getAdmin();
            TableName tableName = TableName.valueOf(newTableForm.getName());
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);                       
//            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("dasdas"));
            // ... with two column families
            tableDescriptor.addFamily(new HColumnDescriptor("tags"));
            tableDescriptor.addFamily(new HColumnDescriptor("data"));

//            admin.createTable(tableDescriptor);
            map.put("tablename", newTableForm.getName());
            map.put("ColumnFamilys", newTableForm.getColumnFamilys());

        } catch (Exception ce) {
            ce.printStackTrace();

        }

        return "CreateTableSuccess";
    }
//    @RequestMapping(method = RequestMethod.POST)
//    public String processSignup(NewTableForm signupForm, BindingResult result) {
//
//        return "signup-success";
//    }
}
