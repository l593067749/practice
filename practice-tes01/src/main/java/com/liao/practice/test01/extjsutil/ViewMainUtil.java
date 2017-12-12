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
 * Created by liaowenqiang on 2017/8/24.
 */
public class ViewMainUtil extends BaseUtils {
    static Map<String,String> typeConvert=new HashMap<String, String>()
    {
        {
            put("BIGINT UNSIGNED","int");
            put("SMALLINT UNSIGNED","int");
            put("TINYINT UNSIGNED","int");
            put("INT UNSIGNED","int");
            put("VARCHAR","string");
            put("DATE","date");
            put("DATETIME","datetime");
        }

    };

    public static void main(String[] args) {
        go();
    }
    public static void go() {
        DbUtils dbUtils=new DbUtils();
        try{

            Connection connection=dbUtils.getConn();
            DatabaseMetaData m_DBMetaData =connection.getMetaData();

            String columnName;
            String columnType;
            ResultSet colRet = m_DBMetaData.getColumns(null,"%", dbTableName,"%");

            StringBuffer sb=new StringBuffer();
            sb.append("Ext.define(\"HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"\", {\n" +
                    "\textend: \"Ext.panel.Panel\",\n" +
                    "\n" +
                    "\tcontroller: \"gdc-"+bigName+"-"+smallName+"\",\n" +
                    "\tviewModel: {\n" +
                    "\t\ttype: \"gdc-"+bigName+"-"+smallName+"\"\n" +
                    "\t},\n" +
                    "\n" +
                    "\trequires: [\n" +
                    "\t\t\"HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"Controller\",\n" +
                    "\t\t\"HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"Model\"\n" +
                    "\t],\n" +
                    "\n" +
                    "\txtype: \"gdc-"+bigName+"-"+smallName+"\",\n" +
                    "\n" +
                    "\tlayout: {\n" +
                    "\t\ttype: 'vbox',\n" +
                    "\t\talign: 'stretch'\n" +
                    "\t},\n" +
                    "\n" +
                    "\t// Create a session for this view\n" +
                    "\tsession: true,\n" +
                    "\n" +
                    "\titems: [{\n" +
                    "\n" +
                    "\t\tflex: 1,\n" +
                    "\t\tid: 'gdc-"+bigName+"-"+smallName+"-grid',\n" +
                    "\t\txtype: 'grid',\n" +
                    "\t\tbind: {\n" +
                    "\t\t\tstore: \"{"+toUpperCaseFirstOne(smallName)+"Store}\"\n" +
                    "\t\t},\n" +
                    "\n" +
                    "\t\tselType: 'checkboxmodel',\n" +
                    "\t\tcolumns: [\n\t\t");


                    sb.append("");

            String tableStr="";
            List<String> tdList=new ArrayList<String>();
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

                String str="{\n"+
                        "\t\t\ttext: '"+remarks+"',\n" +
                        "\t\t\tdataIndex: '"+columnName+"',\n" ;
                if("DATE".equals(columnType)){
                    str+="\t\t\txtype: 'datecolumn',\n" +
                            "\t\t\tformat: 'Y-m-d',\n";
                }else if("DATETIME".equals(columnType)){
                    str+="\t\t\txtype: 'datecolumn',\n" +
                            "\t\t\tformat: 'Y-m-d H:i:s',\n";
                }
                if(columnType.contains("INT")){
                    str+="\t\t\tfilter: {\n" +
                            "\t\t\t\ttype: 'number'\n" +
                            "\t\t\t}\n" +
                            "\t\t},"
                    ;
                }else{
                    str+="\t\t\tfilter: {\n" +
                            "\t\t\t\ttype: 'string'\n" +
                            "\t\t\t}\n" +
                            "\t\t},"
                    ;
                }

                sb.append(str);


                String tdStr="\t\t\t\t'<td  class=\"grid-rowexpander-table-row-title\"><b>"+remarks+":</b></td>',\n";
                if("DATE".equals(columnType)){
                    tdStr+="\t\t\t\t'<td>{"+columnName+":date(\"Y-m-d\")}</td>',\n";
                }else if("DATETIME".equals(columnType)){
                    tdStr+="\t\t\t\t'<td>{"+columnName+":date(\"Y-m-d H:i:s\")}</td>',\n";
                }else{
                    tdStr+="\t\t\t\t'<td>{"+columnName+"}</td>',\n";
                }
                tdList.add(tdStr);
            }

            List<List<String>> lists=splitList(tdList,3);
            for(int i=0;i<lists.size();i++){
                tableStr+="\t\t\t\t'<tr class=\"grid-rowexpander-table-row\">',\n";
                List<String> tempList=lists.get(i);
                for(int j=0;j<tempList.size();j++){
                    tableStr+=tempList.get(j);
                }
                tableStr+="\t\t\t\t'</tr>',\n";
            }

                    sb.append( " {\n" +
                            "\t\t\txtype: 'widgetcolumn',\n" +
                            "\t\t\twidth: 90,\n" +
                            "\t\t\twidget: {\n" +
                            "\t\t\t\txtype: 'button',\n" +
                            "\t\t\t\ttext: '修改',\n" +
                            "\t\t\t\ticonCls: 'fa fa-pencil-square-o fa-lg',\n" +
                            "\t\t\t\thandler: 'on"+toUpperCaseFirstOne(smallName)+"DataEditHandler'\n" +
                            "\t\t\t}\n" +
                            "\t\t}, {\n" +
                            "\t\t\txtype: 'widgetcolumn',\n" +
                            "\t\t\twidth: 80,\n" +
                            "\t\t\ttext: '操作',\n" +
                            "\t\t\twidget: {\n" +
                            "\t\t\t\txtype: 'button',\n" +
                            "\t\t\t\ttext: '删除',\n" +
                            "\t\t\t\ticonCls: 'fa fa-trash-o fa-lg',\n" +
                            "\t\t\t\thandler: 'on"+toUpperCaseFirstOne(smallName)+"DataRemoveHandler'\n" +
                            "\t\t\t}\n" +
                            "\t\t}],\n" +
                            "\t\tplugins: [{\n" +
                            "\t\t\tptype: 'gridfilters'\n" +
                            "\t\t}, {\n" +
                            "\t\t\tptype: 'rowexpander',\n" +
                            "\t\t\trowBodyTpl: new Ext.XTemplate(\n" +
                            "\t\t\t\t'<table class=\"grid-rowexpander-table\">',\n" +tableStr+
                            "\t\t\t\t'</table>',{\n" +
                            "\t\t\t\t\tgetImg:function(img){\n" +
                            "\t\t\t\t\t\ttry{\t\t\t\t\t\t\n" +
                            "\t\t\t\t\t\t\treturn Ext.JSON.decode(decodeURIComponent(img)).base64Img;\n" +
                            "\t\t\t\t\t\t}catch(err){\n" +
                            "\t\t\t\t\t\t\treturn undefined;\n" +
                            "\t\t\t\t\t\t}\n" +
                            "\t\t\t\t\t}\n" +
                            "\t\t\t\t},{\n" +
                            "\t\t\t\t\tgetImgType:function(img){\n" +
                            "\t\t\t\t\t\ttry{\t\n" +
                            "\t\t\t\t\t\t\treturn Ext.JSON.decode(decodeURIComponent(img)).fileType.substr(1);\n" +
                            "\t\t\t\t\t\t}catch(err){\n" +
                            "\t\t\t\t\t\t\treturn undefined;\n" +
                            "\t\t\t\t\t\t}\n" +
                            "\t\t\t\t\t}\n" +
                            "\t\t\t\t}, {\n" +
                            "\t\t\t\t\tgetImgUrl: function(img) {\n" +
                            "\t\t\t\t\t\treturn HUPU_MANAGE.image_server_host + '/' + img;\n" +
                            "\t\t\t\t\t}\n" +
                            "\t\t\t\t}\n" +
                            "\t\t\t)\n" +
                            "\t\t}],\n" +
                            "\t\tloadMask: true,\n" +
                            "\t\tdockedItems: [{\n" +
                            "\t\t\txtype: 'pagingtoolbar',\n" +
                            "\t\t\tbind: {\n" +
                            "\t\t\t\tstore: \"{"+toUpperCaseFirstOne(smallName)+"Store}\"\n" +
                            "\t\t\t},\n" +
                            "\t\t\tdock: 'bottom',\n" +
                            "\t\t\tdisplayInfo: true\n" +
                            "\t\t}, {\n" +
                            "\t\t\txtype: 'toolbar',\n" +
                            "\t\t\tdock: 'top',\n" +
                            "\t\t\tlayout: {\n" +
                            "\t\t\t\tpack: 'left'\n" +
                            "\t\t\t},\n" +
                            "\t\t\tdefaults: {\n" +
                            "\t\t\t\tminWidth: 80\n" +
                            "\t\t\t},\n" +
                            "\t\t\titems: [{\n" +
                            "\t\t\t\ttext: '新建',\n" +
                            "\t\t\t\ticonCls: 'fa fa-plus fa-lg',\n" +
                            "\t\t\t\titemId: 'btnCreate"+toUpperCaseFirstOne(smallName)+"Data'\n" +
                            "\t\t\t}, '-', {\n" +
                            "\t\t\t\titemId: 'btnRemove"+toUpperCaseFirstOne(smallName)+"DataCheckbox',\n" +
                            "\t\t\t\ticonCls: 'fa fa-trash-o fa-lg',\n" +
                            "\t\t\t\ttext: '删除'\n" +
                            "\t\t\t}]\n" +
                            "\t\t}]\n" +
                            "\t}]\n" +
                            "});");

            String fileName=toUpperCaseFirstOne(smallName)+"_viewMain.js";
            File dirFile = new File(filePath+fileName);
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

}
