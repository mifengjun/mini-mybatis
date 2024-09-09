package org.mi6n.minimybatis.mapping;

import java.util.Map;

/**
 * @author mifengjun@gmail.com
 */
public class BoundSql {
    private String sql;
    private String parameterType;
    private String resultType;
    private Map<Integer, String> parameterMap;

    public BoundSql(String sql, String parameterType, String resultType, Map<Integer, String> parameterMap) {
        this.sql = sql;
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.parameterMap = parameterMap;
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
