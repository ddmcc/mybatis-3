package com.ddmcc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class Main {

    public static void main(String[] args) throws Exception{
        SqlSessionFactory sqlSessionFactory;
        try (Reader reader = Resources.getResourceAsReader("com/ddmcc/mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = userMapper.getUser("1");
        User user2 = sqlSession.getMapper(UserMapper.class).getUser("1");
        System.out.println(String.format("user1 == user2 ? %s", user1 == user2));
        sqlSession.commit();

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        User user3 = sqlSession1.getMapper(UserMapper.class).getUser("1");
        System.out.println(String.format("user1 == user3 ? %s", user1 == user3));


        sqlSession.clearCache();

    }

}
