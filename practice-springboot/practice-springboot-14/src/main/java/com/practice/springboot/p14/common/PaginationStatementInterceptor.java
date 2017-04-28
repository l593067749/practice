package com.practice.springboot.p14.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}), @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PaginationStatementInterceptor implements Interceptor {
    private final static Log log = LogFactory
            .getLog(PaginationStatementInterceptor.class);

    public static final ThreadLocal<Pagination> localPage = new ThreadLocal<Pagination>();

    /**
     * 开始分页
     *
     * @param pageNum
     * @param pageSize
     */
    public static void startPage(int pageNum, int pageSize) {
        localPage.set(new Pagination(pageNum, pageSize));
    }

    /**
     * 结束分页并返回结果，该方法必须被调用，否则localPage会一直保存下去，直到下一次startPage
     *
     * @return
     */
    public static Pagination endPage() {
        Pagination page = localPage.get();
        localPage.remove();
        return page;
    }

    public Object intercept(Invocation invocation) throws Throwable {

        if (localPage.get() == null) {//没有分页直接传递
            return invocation.proceed();
        }
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            //借助于MetaObject.forObject来修改statementHandler私有属性
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
            // 可以分离出最原始的的目标类)
            while (metaStatementHandler.hasGetter("h")) {
                Object object = metaStatementHandler.getValue("h");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            // 分离最后一个代理对象的目标类
            while (metaStatementHandler.hasGetter("target")) {
                Object object = metaStatementHandler.getValue("target");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }

            //拿到配置属性值
            Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
            Dialect.Type databaseType = null;
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

            try {
                databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
            } catch (Exception e) {
                throw new Exception("Generate SQL: Obtain DatabaseType Failed!");
            }
            //数据库方言判断
            Dialect dialect = null;
            switch (databaseType) {
                case MYSQL:
                    dialect = new MySql5Dialect();
                    break;
                case ORACLE:
                    //dialect = new OracleDialect();
                    break;
                case SQLSERVER:
                    //dialect = new SQLServer2005Dialect();
                    break;
            }
            //转换为带分页的查询语句
            Pagination pagination = localPage.get();
            String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
            String limitSql=dialect.getLimitString(originalSql, pagination.getOffset(), pagination.getLimit());
            metaStatementHandler.setValue("delegate.boundSql.sql", limitSql);

            //设置总共页数之类的
            Connection connection = (Connection) invocation.getArgs()[0];
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            dialect.setPageParameter(originalSql, connection, mappedStatement, boundSql, pagination);

           // if (log.isDebugEnabled()) {
                log.error("Generate SQL : " + limitSql);
            //}
            return invocation.proceed();
        } else if (invocation.getTarget() instanceof ResultSetHandler) {
            Object result = invocation.proceed();
            Pagination page = localPage.get();
            page.setList((List) result);
            return result;
        }
        return  null;
    }

    public Object plugin(Object target) {
        if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    public void setProperties(Properties properties) {
    }
}
