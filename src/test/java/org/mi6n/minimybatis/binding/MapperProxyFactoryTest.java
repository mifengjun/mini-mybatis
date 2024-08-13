package org.mi6n.minimybatis.binding;

import org.junit.jupiter.api.Test;
import org.mi6n.minimybatis.io.Resource;
import org.mi6n.minimybatis.session.SqlSession;
import org.mi6n.minimybatis.session.SqlSessionFactory;
import org.mi6n.minimybatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

class MapperProxyFactoryTest {

    @Test
    public void test_load_resource_mapper_and_execute() {
        Reader resourceAsReader = Resource.getResourceAsReader("mini-mybatis.config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsReader);
        SqlSession sqlSession = build.openSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);
        String result = mapper.findUserByUsername("123");
        System.out.println("result = " + result);
    }

}