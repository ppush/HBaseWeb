/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbaseweb.springform.form;
/**
 *
 * @author vahan
 */
public class NewTableForm {

    private String name;
    private String[] ColumnFamilysList;
    private String ColumnFamilys;
    

    public String[] getColumnFamilysList() {
        return ColumnFamilysList;
    }
    public String getColumnFamilys() {
        return ColumnFamilys;
    }
    public void setColumnFamilys(String strColumnFamilys) {           
        this.ColumnFamilys = strColumnFamilys;        
        this.ColumnFamilysList = strColumnFamilys.split("\r?\n");
//        System.out.println(this.ColumnFamilysList.length);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
