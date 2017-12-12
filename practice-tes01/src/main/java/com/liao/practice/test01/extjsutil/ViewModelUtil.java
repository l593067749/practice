package com.liao.practice.test01.extjsutil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by liaowenqiang on 2017/8/24.
 */
public class ViewModelUtil extends BaseUtils {
    public static void main(String[] args) {
        go();
    }
    public static void go() {
        try{
            StringBuffer sb=new StringBuffer();
            sb.append("Ext.define('HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"Model', {\n" +
                    "    extend: 'Ext.app.ViewModel',\n" +
                    "    alias: 'viewmodel.gdc-"+bigName+"-"+smallName+"',\n" +
                    "    requires:[\n" +
                    "    \t\"HuPu.store.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"\"\n" +
                    "    ],\n" +
                    "    stores:{\n" +
                    "    \t"+toUpperCaseFirstOne(smallName)+"Store:{\n" +
                    "    \t\ttype:'gdc-"+bigName+"-"+smallName+"',\n" +
                    "    \t\tsession: true\n" +
                    "    \t}\n" +
                    "    }\n" +
                    "\n" +
                    "});");
            String fileName=toUpperCaseFirstOne(smallName)+"Model.js";
            File dirFile = new File(filePath+fileName);
            if (!dirFile.exists()) {
                dirFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(dirFile);
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
        }catch (Exception e){

        }
    }
}
