package com.liao.practice.test01.extjsutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * @描述：公共数据访问交互工具类（注意：在其他机器使用数据库，请修改数据库连接账号(username)及密码(password)，连接地址(url)"）
 * @作者：KingTiger
 * @版本：1.0
 * @开发时间：2013-4-20上午11:30:10
 */
public class DbUtils {
private Connection conn = null;
private String username = "gdc";
private String password = "gdckog";
private String url="jdbc:mysql://GDC-KOG-db-01.offline.hupu.com:3306/gdc_kog_hupu";
/**
 * @描述:用来在类中最先执行数据库的驱动加载
 * @作者：KingTiger
 */
static {
	try{
		Class.forName("com.mysql.jdbc.Driver");
	}catch(ClassNotFoundException e){
		System.out.println("找不到类，请检查驱动包是否导入");
		e.printStackTrace();
	}	
	}	
/**
 * @描述：建立数据库连接并返回给开发者连接对象
 * @作者：KingTiger
 * @参数：@return
 * @返回值：Connection
 */
public Connection getConn(){
	try{
		conn = DriverManager.getConnection(url,username,password);
		return conn;
	}catch(Exception e){
		System.out.println("连接不上数据库，请检查连接地址（url）,账号（username）,密码（password）");
		e.printStackTrace();
		return null;
	}
}



/**
 * @描述：释放连接时内存
 * @作者：KingTiger
 * @参数：无
 * @返回值：void
 */
public void close(){
	if(conn != null){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
}

