package com.thoughtworks.twu.persistence;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.*;

@MappedTypes(DateTime.class)
public class DateTimeTypeHandler implements TypeHandler<DateTime> {

    public static final DateTimeZone DATE_TIME_ZONE = DateTimeZone.UTC;

    @Override
    public void setParameter(PreparedStatement statement, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            statement.setTimestamp(i, new Timestamp((parameter).getMillis()));
        } else {
            statement.setTimestamp(i, null);
        }
    }

    @Override
    public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        if (timestamp != null) {
            return new DateTime(timestamp.getTime(), DATE_TIME_ZONE);
        } else {
            return null;
        }
    }

    @Override
    public DateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp != null) {
            return new DateTime(timestamp.getTime(), DATE_TIME_ZONE);
        } else {
            return null;
        }
    }

    @Override
    public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp != null) {
            return new DateTime(timestamp.getTime(), DATE_TIME_ZONE);
        } else {
            return null;
        }
    }

}
