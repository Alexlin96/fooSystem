package com.pure.common;

public abstract class Dialect {
	public static enum Type {
		MYSQL, ORACLE,SQLSERVER
	}

	public abstract String getLimitString(String sql, int offset, int limit);
}
