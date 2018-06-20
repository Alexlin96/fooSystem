package com.pure.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class EmptyStringIfNull implements TypeHandler<String> {

	@Override
	public String getResult(ResultSet rs, String columnName)
			throws SQLException {
		return (rs.getString(columnName) == null) ? "" : rs
				.getString(columnName);
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		return (rs.getString(columnIndex) == null) ? "" : rs
				.getString(columnIndex);
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return (cs.getString(columnIndex) == null) ? "" : cs
				.getString(columnIndex);
	}

	@Override
	public void setParameter(PreparedStatement ps, int arg1, String str,
			JdbcType jdbcType) throws SQLException {
	}
}