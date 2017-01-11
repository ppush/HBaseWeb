/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbaseweb.controllers;

import com.google.protobuf.ServiceException;
import hbaseweb.springform.form.NewTableForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Delete;

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
        return "CreateTable";
    }

    @RequestMapping(value = "/deleterow/{tablename}/{row}", method = RequestMethod.GET)
    public String deleterow(@PathVariable(value = "tablename") String tablename, @PathVariable(value = "row") String row, ModelMap map) {
        try {
            Configuration config = HBaseConfiguration.create();
            config.clear();
            config.set("hbase.zookeeper.quorum", "192.168.10.50");
            config.set("hbase.zookeeper.property.clientPort", "2181");
            HBaseAdmin.checkHBaseAvailable(config);
//            Connection connection = ConnectionFactory.createConnection(config);    
            HTable hTable = new HTable(config, tablename);
            // Instantiating Delete class
            Delete delete = new Delete(Hex.decodeHex(row.toCharArray()));
//            delete.addFamily(family);
            // deleting the data
            hTable.delete(delete);
            // closing the HTable object
            hTable.close();

        } catch (ZooKeeperConnectionException ex) {
            Logger.getLogger(CreateTableController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(CreateTableController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateTableController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DecoderException ex) {
            Logger.getLogger(CreateTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/?tablename=" + tablename;
    }

    @RequestMapping(value = "/deletetable/{tablename}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "tablename") String tablename, ModelMap map) {

        try {

            Configuration config = HBaseConfiguration.create();
            config.clear();
            config.set("hbase.zookeeper.quorum", "192.168.10.50");
            config.set("hbase.zookeeper.property.clientPort", "2181");
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Admin admin = connection.getAdmin();
            TableName tableName = TableName.valueOf(tablename);
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            admin.deleteTable(tableName);
        } catch (Exception ce) {
            ce.printStackTrace();
            map.put("error", ce);
            return "delete";
        }

        return "delete";
    }

    @RequestMapping(value = "/disable/{tablename}", method = RequestMethod.GET)
    public String Disable(@PathVariable(value = "tablename") String tablename, ModelMap map) {

        try {

            Configuration config = HBaseConfiguration.create();
            config.clear();
            config.set("hbase.zookeeper.quorum", "192.168.10.50");
            config.set("hbase.zookeeper.property.clientPort", "2181");
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Admin admin = connection.getAdmin();
            TableName tableName = TableName.valueOf(tablename);
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            admin.disableTable(tableName);
        } catch (Exception ce) {
            ce.printStackTrace();
            map.put("error", ce);
            return "delete";
        }

        return "delete";
    }

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    public String newtable(NewTableForm newTableForm, ModelMap map) {

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
            String[] Famyly = newTableForm.getColumnFamilysList();
            for (int i = 0; i < Famyly.length; i++) {
                tableDescriptor.addFamily(new HColumnDescriptor(Famyly[i]));
            }

            admin.createTable(tableDescriptor);
            map.put("tablename", newTableForm.getName());
            map.put("ColumnFamilys", newTableForm.getColumnFamilys());

//            System.out.println(newTableForm.getColumnFamilys().length);
        } catch (Exception ce) {
            ce.printStackTrace();
            map.put("newTableForm", newTableForm);
            map.put("error", ce);
            return "CreateTable";
        }

        return "CreateTableSuccess";
    }

    @RequestMapping(value = "/insert/{tablename}", method = RequestMethod.GET)
    public String insert(@PathVariable(value = "tablename") String tablename, ModelMap map) {

        try {

            Configuration config = HBaseConfiguration.create();
            config.clear();
            config.set("hbase.zookeeper.quorum", "192.168.10.50");
            config.set("hbase.zookeeper.property.clientPort", "2181");
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Admin admin = connection.getAdmin();
//            TableName tableName = TableName.valueOf(tablename);
            HTableDescriptor tableDescriptor = admin.getTableDescriptor(TableName.valueOf(tablename));
            HColumnDescriptor[] colfamilis = tableDescriptor.getColumnFamilies();
            map.put("table", tableDescriptor);
            map.put("colfamilis", colfamilis);
//            admin.deleteTable(tableName);
        } catch (Exception ce) {
            ce.printStackTrace();
            map.put("error", ce);
            return "insert";
        }

        return "insert";
    }
}
