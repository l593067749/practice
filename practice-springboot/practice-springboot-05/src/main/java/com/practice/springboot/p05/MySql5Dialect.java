package com.practice.springboot.p05;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySql5Dialect extends Dialect {

	private final static Log logger = LogFactory
			.getLog(MySql5Dialect.class);
	public String getLimitString(String querySqlString, int offset, int limit) {
        return querySqlString + " limit " + offset + " ," + limit;
	}

	@Override
	public String getCountString(String querySqlString) {

		int limitIndex = querySqlString.lastIndexOf("limit");

		if(limitIndex != -1){
			querySqlString = querySqlString.substring(0, limitIndex != -1 ? limitIndex : querySqlString.length() - 1);
		}

                // 用的过程中会发现这里对原有sql进行包装一层select count会有SQL效率低的问题
                // 等待优化
		return "SELECT COUNT(*) FROM (" + querySqlString + ") tem";
	}
	/**
	 * 获取总记录数
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	public void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,
								  BoundSql boundSql, Pagination page) {
		// 记录总记录数
		String countSql = "select count(0) from (" + sql + ") temp";
		logger.error(countSql);
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
					boundSql.getParameterMappings(), boundSql.getParameterObject());
			setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			page.setTotal(totalCount);
			int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
			page.setMaxPage(totalPage);;
		} catch (SQLException e) {
			logger.error("Ignore this exception", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
			try {
				countStmt.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
		}
	}
	/**
	 * 代入参数值
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
							   Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

	public boolean supportsLimit() {
		return true;
	}
}