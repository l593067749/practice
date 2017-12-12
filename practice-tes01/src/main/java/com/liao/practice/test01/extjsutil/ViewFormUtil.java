package com.liao.practice.test01.extjsutil;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by liaowenqiang on 2017/8/24.
 */
public class ViewFormUtil extends BaseUtils {

    static Set<String> excludeFilds=new HashSet<String>()
    {
        {
            add("create_at");
            add("update_at");
        }

    };

    public static void main(String[] args) {
        go();
    }

    public static void go() {
        DbUtils dbUtils=new DbUtils();
        try{

            StringBuffer sb=new StringBuffer();
            Connection connection=dbUtils.getConn();
            DatabaseMetaData m_DBMetaData =connection.getMetaData();
            String columnName;
            String columnType;
            ResultSet colRet = m_DBMetaData.getColumns(null,"%", dbTableName,"%");

            sb.append("Ext.define(\"HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"Form\", {\n" +
                    "    extend: \"Ext.window.Window\",\n" +
                    "\n" +
                    "    controller: \"gdc-"+bigName+"-"+smallName+"\",\n" +
                    "    xtype: \"gdc-"+bigName+"-"+smallName+"form\",\n" +
                    "\n" +
                    "    requires: [\n" +
                    "        \"Ext.form.field.ImageFile\"\n" +
                    "    ],\n" +
                    "\n" +
                    "    layout: 'fit',\n" +
                    "    modal: true,\n" +
                    "    width: 900,\n" +
                    "    closable: true,\n" +
                    "\n" +
                    "    bind: {\n" +
                    "        title: '{title}'\n" +
                    "    },\n" +
                    "\n" +
                    "    items: {\n" +
                    "        xtype: 'form',\n" +
                    "        resizable: true,\n" +
                    "\n" +
                    "        id: 'gdc-"+bigName+"-"+smallName+"-form',\n" +
                    "        layout: 'column',\n" +
                    "\n" +
                    "        defaults: {\n" +
                    "            layout: 'form',\n" +
                    "            xtype: 'container',\n" +
                    "            defaultType: 'textfield',\n" +
                    "            style: 'width: 33.33%'\n" +
                    "        },\n" +
                    "\n" +


                    "        items: [");


            List<String> list=new ArrayList<String>();
            while(colRet.next()) {
                columnName = colRet.getString("COLUMN_NAME");
                columnType = colRet.getString("TYPE_NAME");
                String remarks = colRet.getString("REMARKS");
                int datasize = colRet.getInt("COLUMN_SIZE");
                int nullable = colRet.getInt("NULLABLE");
                System.out.println(columnName+" "+columnType+" "+remarks+" "+nullable);
                if(excludeFilds.contains(columnName)){
                    continue;
                }


                String str="{\n" +
                        "                fieldLabel: '"+remarks+"',\n" +
                        "                name: '"+columnName+"',\n" +
                        "                allowBlank: "+(nullable==0?"false":"true")+",\n" +
                        "                maxLength: "+datasize+",\n";

                if(columnType.contains("INT")){
                    str+="                xtype: 'numberfield',\n" +
                            "                minValue: 0,\n";
                }else if("VARCHAR".equals(columnType)){
                    if(remarks.contains("片")||remarks.contains("照")||remarks.contains("像")||remarks.contains("图")){
                        str+="                xtype: 'imagefilefield',\n" +
                                "                buttonConfig: {\n" +
                                "                    iconCls: 'fa fa-picture-o fa-lg'\n" +
                                "                },\n" +
                                "                buttonText: '',\n" +
                                "                beforeSetValueHandler:function(data){\n" +
                                "                    var msg = '';\n" +
                                "                    data.fileSize/1024>500?msg+='图片大小最好不要超过500KB':true;\n" +
                                "                    msg.length > 0 ? msg+='<br>':true;\n" +
                                "                    (data.fileWidth<65 || data.fileHeight<90)?msg+='推荐尺寸大于等于65x90(现在图片尺寸:'+data.fileWidth+'x'+data.fileHeight+')':true;\n" +
                                "                    msg.length > 0 ? msg+='<br>':true;\n" +
                                "                    (data.fileType.toLocaleLowerCase().indexOf('png') < 0 && data.fileType.toLocaleLowerCase().indexOf('jpg') < 0) ? msg+='图片格式错误(此图格式:'+data.fileType+')':true;\n" +
                                "                    msg.length > 0 && Ext.MessageBox.show({\n" +
                                "                        title: '注意',\n" +
                                "                        msg: msg,\n" +
                                "                        scope: this,\n" +
                                "                        icon: Ext.MessageBox.WARNING\n" +
                                "                    });\n" +
                                "                    if(msg.length > 0){\n" +
                                "                        this.isSetValue = false;\n" +
                                "                    }else{\n" +
                                "                        this.isSetValue = true;\n" +
                                "                    }\n" +
                                "                },\n";
                    }else{
                        str+="                xtype: 'textfield',\n";
                    }
                }else if("DATE".equals(columnType)){
                    str+="                format: 'Y-m-d',\n" +
                            "                xtype: 'datefield',\n" +
                            "                anchor: '100%',\n" +
                            "                submitFormat :'timestamp',\n";
                }else if("DATETIME".equals(columnType)){
                    str+="                format: 'Y-m-d H:i:s',\n" +
                            "                xtype: 'datefield',\n" +
                            "                anchor: '100%',\n" +
                            "                submitFormat :'timestamp',\n";
                }else {
                    str+="                xtype: 'textfield',\n";
                }
                str+=  "                bind: '{theRecord."+columnName+"}'\n" +
                        "            }";

                list.add(str);
            }
            int pageSize=list.size()%3==0?list.size()/3:list.size()/3+1;
            List<List<String>> lists=splitList(list,pageSize);


            for(int i=0;i<lists.size();i++){

                    sb.append("{\n" +
                            "            items: [");

                List<String> currList= lists.get(i);
                for(int j=0;j<currList.size();j++){
                    if(j==currList.size()-1){
                        sb.append(currList.get(j));
                    }else{
                        sb.append(currList.get(j)+",");
                    }
                }

                if(i==lists.size()-1){
                    sb.append("]\n" +
                            "        }");
                }
                if(i<lists.size()-1){
                    sb.append("]\n" +
                            "        },");
                }

            }

            sb.append("]\n" +
                    "    },\n" +
                    "\n" +
                    "    buttons: [{\n" +
                    "        id: 'btnSave"+toUpperCaseFirstOne(smallName)+"',\n" +
                    "        text: 'Save'\n" +
                    "    }, {\n" +
                    "        id: \"btnCancel"+toUpperCaseFirstOne(smallName)+"\",\n" +
                    "        text: 'Cancel'\n" +
                    "    }]\n" +
                    "});");


            String fileName=toUpperCaseFirstOne(smallName)+"Form.js";
            File dirFile = new File(filePath+fileName);
            if (!dirFile.exists()) {
                dirFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(dirFile);
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
            colRet.close();

        }catch (Exception e){

        }
    }


}
