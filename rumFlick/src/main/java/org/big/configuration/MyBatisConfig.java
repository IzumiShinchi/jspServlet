package org.big.configuration;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public TypeHandlerRegistry typeHandlerRegistry() {
        TypeHandlerRegistry registry = new TypeHandlerRegistry();
        registry.register(LocalDateTime.class, LocalDateTimeTypeHandler.class);
        return registry;
    }

    public static class LocalDateTimeTypeHandler implements TypeHandler<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        @Override
        public void setParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
            if (parameter != null) {
                ps.setTimestamp(i, Timestamp.valueOf(parameter));
            } else {
                ps.setNull(i, Types.TIMESTAMP);
            }
        }

        @Override
        public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
            Timestamp timestamp = rs.getTimestamp(columnName);
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        }

        @Override
        public LocalDateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
            Timestamp timestamp = rs.getTimestamp(columnIndex);
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        }

        @Override
        public LocalDateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
            Timestamp timestamp = cs.getTimestamp(columnIndex);
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        }
    }
}
