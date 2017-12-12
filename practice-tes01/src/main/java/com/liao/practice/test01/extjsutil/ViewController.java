package com.liao.practice.test01.extjsutil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by liaowenqiang on 2017/8/24.
 */
public class ViewController extends BaseUtils {
    public static void main(String[] args) {
        go();
    }
    public static void go() {

        try {
            StringBuffer sb=new StringBuffer();

            sb.append("Ext.define('HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"Controller', {\n" +
                    "    extend: 'Ext.app.ViewController',\n" +
                    "    alias: 'controller.gdc-"+bigName+"-"+smallName+"',\n" +
                    "\n" +
                    "    requires: [\n" +
                    "\t\t'Ext.window.Window',\n" +
                    "\t\t\"HuPu.view.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"Form\"\n" +
                    "\t],\n" +
                    "\n" +
                    "\tcontrol: {\n" +
                    "\t\t\"gdc-"+bigName+"-"+smallName+" button[itemId='btnCreate"+toUpperCaseFirstOne(smallName)+"Data']\": {\n" +
                    "\t\t\tclick: 'on"+toUpperCaseFirstOne(smallName)+"DataCreateHandler'\n" +
                    "\t\t},\n" +
                    "\t\t\"gdc-"+bigName+"-"+smallName+" button[itemId='btnRemove"+toUpperCaseFirstOne(smallName)+"DataCheckbox']\": {\n" +
                    "\t\t\tclick: 'on"+toUpperCaseFirstOne(smallName)+"DataRemoveHandler'\n" +
                    "\t\t},\n" +
                    "\t\t\"gdc-"+bigName+"-"+smallName+"form #btnSave"+toUpperCaseFirstOne(smallName)+"\": {\n" +
                    "\t\t\tclick: 'onSave"+toUpperCaseFirstOne(smallName)+"'\n" +
                    "\t\t},\n" +
                    "\t\t\"gdc-"+bigName+"-"+smallName+"form #btnCancel"+toUpperCaseFirstOne(smallName)+"\": {\n" +
                    "\t\t\tclick: 'onCancel"+toUpperCaseFirstOne(smallName)+"'\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\n" +
                    "\ton"+toUpperCaseFirstOne(smallName)+"DataCreateHandler: function(button) {\n" +
                    "\t\tthis.createDialog(null);\n" +
                    "\t},\n" +
                    "\ton"+toUpperCaseFirstOne(smallName)+"DataRemoveHandler: function(btn) {\n" +
                    "\n" +
                    "\t\tvar view = this.getView(),\n" +
                    "\t\t\tgridView = view.down('#gdc-"+bigName+"-"+smallName+"-grid');\n" +
                    "\t\tif (!gridView)\n" +
                    "\t\t\treturn;\n" +
                    "\t\tvar store = gridView.getStore();\n" +
                    "\n" +
                    "\t\tif (btn.getItemId() === 'btnRemove"+toUpperCaseFirstOne(smallName)+"DataCheckbox') {\n" +
                    "\t\t\t//checkbox删除处理\n" +
                    "\t\t\tvar selectedRows = gridView.getSelectionModel().getSelection();\n" +
                    "\t\t\tif (selectedRows.length) {\n" +
                    "\t\t\t\tExt.MessageBox.confirm('操作提示', '确定要删除这些选中数据?', function(isYes) {\n" +
                    "\t\t\t\t\tisYes === 'yes' && store.remove(selectedRows) && store.reload();;\n" +
                    "\t\t\t\t}, this);\n" +
                    "\t\t\t} else {\n" +
                    "\t\t\t\t Ext.MessageBox.show({\n" +
                    "\t\t\t\t \ttitle:'提示信息', \n" +
                    "\t\t\t\t \tmsg:'请先选择要删除的数据!',\n" +
                    "\t\t\t\t \tbuttons: Ext.MessageBox.OK,\n" +
                    "\t\t\t\t \ticon:'fa fa-exclamation-triangle fa-4'\n" +
                    "\t\t\t\t });\n" +
                    "\t\t\t}\n" +
                    "\t\t} else {\n" +
                    "\t\t\t//单个行数据删除\n" +
                    "\t\t\tExt.MessageBox.confirm('操作提示', '确定要删除这条数据?', function(isYes) {\n" +
                    "\t\t\t\tisYes === 'yes' && store.remove(btn.getWidgetRecord()) && store.reload();;\n" +
                    "\t\t\t}, this);\n" +
                    "\t\t}\t\t\n" +
                    "\t},\n" +
                    "\tonSave"+toUpperCaseFirstOne(smallName)+": function() {\n" +
                    "\t\tvar view = this.getView(),\n" +
                    "\t\t\tgridView = view.down('#gdc-"+bigName+"-"+smallName+"-grid'),\n" +
                    "\t\t\tformView = view.down('#gdc-"+bigName+"-"+smallName+"-form');\n" +
                    "\t\tif (!gridView)\n" +
                    "\t\t\treturn;\n" +
                    "\t\tvar dialog = this.dialog,\n" +
                    "\t\t\tstore = gridView.getStore(),\n" +
                    "\t\t\tform = formView.getForm(),\n" +
                    "\t\t\tisEdit = this.isEdit,\n" +
                    "\t\t\tid;\n" +
                    "\n" +
                    "\t\tif (form.isValid()) {\n" +
                    "\t\t\tif (!isEdit) {\n" +
                    "\t\t\t\t// Since we're not editing, we have a newly inserted record. Grab the id of\n" +
                    "\t\t\t\t// that record that exists in the child session\n" +
                    "\t\t\t\tid = dialog.getViewModel().get('theRecord').id;\n" +
                    "\t\t\t}\n" +
                    "\t\t\tdialog.getSession().save();\n" +
                    "\t\t\tif (!isEdit) {\n" +
                    "\t\t\t\t// Use the id of that child record to find the phantom in the parent session, \n" +
                    "\t\t\t\t// we can then use it to insert the record into our store\n" +
                    "\t\t\t\tstore.add(this.getSession().getRecord('HuPu.model.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"', id));\n" +
                    "\t\t\t}\n" +
                    "\t\t\t\t\t\tvar config = new Object()\n" +
                    "                        config.errorResponse=\"本次操作成功\";\n" +
                    "                        Ext.ErrorResponseHandler(config);\n" +
                    "\t\t\tthis.onCancel"+toUpperCaseFirstOne(smallName)+"();\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\tonCancel"+toUpperCaseFirstOne(smallName)+": function() {\n" +
                    "\t\t this.dialog = Ext.destroy(this.dialog);\n" +
                    "\t},\n" +
                    "\tcreateDialog: function(record) {\n" +
                    "\t\tvar view = this.getView();\n" +
                    "\n" +
                    "\t\tthis.isEdit = !!record;\n" +
                    "\t\tthis.dialog = view.add({\n" +
                    "\t\t\txtype: 'gdc-"+bigName+"-"+smallName+"form',\n" +
                    "\t\t\tviewModel: {\n" +
                    "\t\t\t\tdata: {\n" +
                    "\t\t\t\t\ttitle: record ? '修改' : '添加'\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t// If we are passed a record, a copy of it will be created in the newly spawned session.\n" +
                    "\t\t\t\t// Otherwise, create a new phantom customer in the child.\n" +
                    "\t\t\t\tlinks: {\n" +
                    "\t\t\t\t\ttheRecord:  record ||  {\n" +
                    "\t\t\t\t\t\ttype: 'HuPu.model.GDC."+bigName.toUpperCase()+"."+smallName+'.'+toUpperCaseFirstOne(smallName)+"',\n" +
                    "\t\t\t\t\t\tcreate: true\n" +
                    "\t\t\t\t\t}\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t},\n" +
                    "\n" +
                    "\t\t\t// Creates a child session that will spawn from the current session\n" +
                    "\t\t\t// of this view.\n" +
                    "\t\t\tsession: true\n" +
                    "\t\t});\n" +
                    "\n" +
                    "\t\tthis.dialog.show();\n" +
                    "\t},\n" +
                    "\ton"+toUpperCaseFirstOne(smallName)+"DataEditHandler:function(button){\n" +
                    "\t\tthis.createDialog(button.getWidgetRecord());\n" +
                    "\t}\n" +
                    "});\n");
            String fileName=toUpperCaseFirstOne(BaseUtils.smallName)+"Controller.js";
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
