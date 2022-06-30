package com.example.test;

import com.example.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SpringBootLearnApplicationTests {

    @Autowired
    private User user;

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() throws SQLException {
        // System.out.println(user);
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

}
