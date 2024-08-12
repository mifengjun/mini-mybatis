package org.mi6n.minimybatis.session;

/**
 * @author mifengjun@gmail.com
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> T getMapper(Class<T> type);
}
