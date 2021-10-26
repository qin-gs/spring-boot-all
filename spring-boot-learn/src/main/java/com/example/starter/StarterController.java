package com.example.starter;

import com.example.module.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用自己starter中的类
 */
@RestController
public class StarterController {

    @Autowired
    private HelloService helloService;

    @GetMapping("starter")
    public String starter() {
        return helloService.sayHello();
    }
}
