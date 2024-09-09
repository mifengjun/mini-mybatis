package org.mi6n.minimybatis.session;

import org.junit.jupiter.api.Test;
import org.mi6n.minimybatis.binding.UserDao;
import org.mi6n.minimybatis.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class SqlSessionFactoryTest {

    @Test
    void openSession() {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resource.getResourceAsReader("mini-mybatis.config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);
        String abc = mapper.findUserByUsername("abc");
        System.out.println("abc = " + abc);
    }
}