package com.liao.practice.test01.extjsutil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by liaowenqiang on 2017/8/25.
 */
public class ExtjsSelectUtil extends BaseUtils {

    public static void main(String[] args) {
        StringBuffer sb=new StringBuffer();

        try{
            sb.append("Ext.define(\"HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+"."+toUpperCaseFirstOne(smallName)+"Select\", {\n" +
                    "\textend: \"Ext.form.field.ComboBox\",\n" +
                    "\n" +
                    "\trequires: [\n" +
                    "\t\t\"HuPu.store.GDC."+bigName.toUpperCase()+"."+smallName+"."+toUpperCaseFirstOne(smallName)+"\"\n" +
                    "\t],\n" +
                    "\n" +
                    "\txtype:'gdc-"+bigName+"-"+smallName+"select',\n" +
                    "\n" +
                    "\tstore:{\n" +
                    "\t\ttype:'gdc-"+bigName+"-"+smallName+"'\n" +
                    "\t},\n" +


                    "tpl: Ext.create('Ext.XTemplate',\n" +
                    "        '<tpl for=\".\">',\n" +
                    "        '<div class=\"x-boundlist-item\">{name_zh}({id})</div>',\n" +
                    "        '</tpl>'\n" +
                    "    ),\n" +
                    "    displayTpl: Ext.create('Ext.XTemplate',\n" +
                    "        '<tpl for=\".\">',\n" +
                    "        '{name_zh}({id})',\n" +
                    "        '</tpl>'\n" +
                    "    ),"
                    +
                    "\tpageSize: 25,\n" +
                    "\tdisplayField: 'name_zh',\n" +
                    "\tvalueField: 'id',\n" +
                    "\ttypeAhead: true,\n" +
                    "\tlistConfig: {\n" +
                    "\t\tminWidth: 350\n" +
                    "\t}\n" +
                    "});");
            String fileName=toUpperCaseFirstOne(smallName)+"Select.js";
            File dirFile = new File(filePath+fileName);
            if (!dirFile.exists()) {
                dirFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(dirFile);
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
