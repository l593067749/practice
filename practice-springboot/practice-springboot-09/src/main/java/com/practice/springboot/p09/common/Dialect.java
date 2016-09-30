package com.practice.springboot.p09.common;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.sql.Connection;

public abstract class Dialect {

	public static enum Type {
        MYSQL, ORACLE, SQLSERVER
	}

	public abstract String getLimitString(String querySqlString, int offset, int limit);

	public abstract String getCountString(String querySqlString);
	/**
	 * 获取总记录数
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	public abstract void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,BoundSql boundSql, Pagination page);

}