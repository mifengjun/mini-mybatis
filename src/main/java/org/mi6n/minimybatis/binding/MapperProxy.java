package org.mi6n.minimybatis.binding;

import org.mi6n.minimybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author mifengjun@gmail.com
 */
public class MapperProxy<T> implements InvocationHandler {
    private final Class<T> mapperInterface;
    private final SqlSession sqlSession;

    public MapperProxy(final Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }else {
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}
