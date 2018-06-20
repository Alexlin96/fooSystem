package com.pure.common;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	private final static Log log = LogFactory
			.getLog(PaginationInterceptor.class);

	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();

		BoundSql boundSql = statementHandler.getBoundSql();

		MetaObject metaStatementHandler = MetaObject.forObject(
				statementHandler, new DefaultObjectFactory(),
				new DefaultObjectWrapperFactory());

		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");

		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {

			return invocation.proceed();

		}

		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");

		Dialect.Type databaseType = null;

		try {

			databaseType = Dialect.Type.valueOf(configuration.getVariables()
					.getProperty("dialect").toUpperCase());

		} catch (Exception e) {

			// ignore

		}

		if (databaseType == null) {

			throw new RuntimeException(
					"the value of the dialect property in configuration.xml is not defined : "
							+ configuration.getVariables().getProperty(
									"dialect"));

		}

		Dialect dialect = null;

		switch (databaseType) {

		case MYSQL:

			dialect = new MysqlDialect();
			break;
		case ORACLE:

			dialect = new OracleDialect();
			break;
		case SQLSERVER:

			dialect = new SQLServerDialect();
			break;

		}

		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");

		metaStatementHandler.setValue("delegate.boundSql.sql", dialect
				.getLimitString(originalSql, rowBounds.getOffset(), rowBounds
						.getLimit()));

		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);

		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);

		if (log.isDebugEnabled()) {

			log.debug("生成分页SQL : " + boundSql.getSql());

		}

		return invocation.proceed();

	}

	public Object plugin(Object target) {

		return Plugin.wrap(target, this);

	}

	public void setProperties(Properties properties) {
	}

}
