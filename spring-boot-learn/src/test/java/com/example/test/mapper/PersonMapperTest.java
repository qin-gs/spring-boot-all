package com.example.test.mapper;

import com.example.bean.Person;
import com.example.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    private PersonMapper mapper;

    @Test
    void queryPersonList() {
        List<Person> person = mapper.queryPersonList();
        System.out.println(person);
    }
}
