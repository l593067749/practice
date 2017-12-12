package com.liao.practice.test01.extjsutil;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liaowenqiang on 2017/8/23.
 */
public class ExtjsModeUtil extends BaseUtils{



    static Map<String,String> typeConvert=new HashMap<String, String>()
    {
        {
            put("BIGINT UNSIGNED","int");
            put("TINYINT UNSIGNED","int");
            put("SMALLINT UNSIGNED","int");
            put("INT UNSIGNED","int");
            put("VARCHAR","string");
            put("DATE","date");
            put("DATETIME","datetime");
        }

    };
    public  static  void go(){
        DbUtils dbUtils=new DbUtils();
        try{

            Connection connection=dbUtils.getConn();
            DatabaseMetaData  m_DBMetaData =connection.getMetaData();

            String columnName;
            String columnType;
            ResultSet colRet = m_DBMetaData.getColumns(null,"%", dbTableName,"%");
            StringBuffer sb=new StringBuffer();
            sb.append("Ext.define('HuPu.model.GDC."+bigName.toUpperCase()+"."+smallName+"."+toUpperCaseFirstOne(smallName)+"', {\n" +
                    "    extend: 'Ext.data.Model',\n" +
                    "\n" +
                    "    fields: [\n"
            );
            while(colRet.next()) {
                columnName = colRet.getString("COLUMN_NAME");
                columnType = colRet.getString("TYPE_NAME");
                String remarks = colRet.getString("REMARKS");
                System.out.println(columnName+" "+columnType+" "+remarks);
                String extjsType=typeConvert.get(columnType);
                if(extjsType==null){
                    System.out.println("注意："+columnName+"--type="+columnType+"--没有匹配类型");
                    extjsType="string";
                }
                if(columnType.contains("INT")){
                    extjsType="int";
                }
                String appStr="";
                if("datetime".equals(extjsType)){
                    appStr=", dateReadFormat: 'timestamp', dateWriteFormat: \"Y-m-d H:i:s\"";
                    extjsType="date";
                }else if("date".equals(extjsType)) {
                    appStr=", dateReadFormat: 'timestamp', dateWriteFormat: \"Y-m-d\"";
                }
                String str="        {name: '"+columnName+"', type: '"+extjsType+"'"+appStr+"}"+(colRet.isLast() ? "":",")+"//"+remarks+"\n";
                sb.append(str);
            }
            sb.append( "    ],\n" +
                    "    identifier: {\n" +
                    "        type: 'sequential',\n" +
                    "        seed: 1000000000,\n" +
                    "        prefix: ''\n" +
                    "    }\n" +
                    "});");
            File dirFile = new File(filePath+toUpperCaseFirstOne(smallName)+"_mode"+".js");
            if (!dirFile.exists()) {
                dirFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(dirFile);
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
            colRet.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtils.close();
        }
    }

    public static void main(String[] args) {

        go();
    }
}
