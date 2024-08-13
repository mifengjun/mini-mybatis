package org.mi6n.minimybatis.mapping;

import org.mi6n.minimybatis.session.Configuration;

import java.util.Map;

/**
 * @author mifengjun@gmail.com
 */
public class MappedStatement {
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;

    private String sql;
    private String parameterType;
    private String resultType;
    private Map<Integer, String> parameterMap;

    public MappedStatement() {
    }

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();
        public Builder (Configuration configuration, String id, SqlCommandType sqlCommandType, String sql, String parameterType, String resultType, Map<Integer, String> parameterMap) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.sql = sql;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.parameterMap = parameterMap;
        }
        public MappedStatement build() {
            return mappedStatement;
        }
    }


    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Map<Integer, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<Integer, String> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
