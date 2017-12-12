package com.liao.practice.test01.extjsutil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by liaowenqiang on 2017/8/24.
 */
public class ExtjsStoreUtil extends BaseUtils {
    public static void go(){
        try{
            StringBuffer sb=new StringBuffer();
            sb.append("Ext.define('HuPu.store.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"', {\n" +
                    "    extend: \"Ext.data.Store\",\n" +
                    "\n" +
                    "    model: 'HuPu.model.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"',\n" +
                    "\n" +
                    "    alias: 'store.gdc-"+bigName+"-"+smallName+"',\n" +
                    "    storeId:'gdc-"+bigName+"-"+smallName+"',\n" +
                    "\n" +
                    "    sorters: [{\n" +
                    "        property: 'id',\n" +
                    "        direction: 'asc'\n" +
                    "    }],\n" +
                    "\n" +
                    "    proxy: {\n" +
                    "        type: 'ajax',\n" +
                    "        writer: {\n" +
                    "            type: 'json',\n" +
                    "            allowSingle: false\n" +
                    "        },\n" +
                    "        api: {\n" +
                    "            create: '/api/GDC-"+bigName.toUpperCase()+"s/gdc/"+bigName+"/"+smallName+"/create',\n" +
                    "            read: '/api/GDC-"+bigName.toUpperCase()+"s/gdc/"+bigName+"/"+smallName+"/list',\n" +
                    "            update: '/api/GDC-"+bigName.toUpperCase()+"s/gdc/"+bigName+"/"+smallName+"/update',\n" +
                    "            destroy: '/api/GDC-"+bigName.toUpperCase()+"s/gdc/"+bigName+"/"+smallName+"/delete'\n" +
                    "        },\n" +
                    "        actionMethods: {\n" +
                    "            create: 'post',\n" +
                    "            read: 'GET',\n" +
                    "            update: 'PUT',\n" +
                    "            destroy: 'DELETE'\n" +
                    "        },\n" +
                    "        reader: {\n" +
                    "            type: 'json',\n" +
                    "            rootProperty: 'list',\n" +
                    "            totalProperty: 'page.total'\n" +
                    "        },\n" +
                    "        filterParam: 'filter'\n" +
                    "    },\n" +
                    "    autoLoad: true,\n" +
                    "    autoSync: true,\n" +
                    "    remoteFilter: true,\n" +
                    "    remoteSort:true\n" +
                    "});");

            String fileName=toUpperCaseFirstOne(smallName)+ "_store.js";
            File dirFile = new File(filePath+fileName);
            if (!dirFile.exists()) {
                dirFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(dirFile);
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
        }catch (Exception e){

        }finally {

        }
    }
    public static void main(String[] args) {

        go();

    }

}
