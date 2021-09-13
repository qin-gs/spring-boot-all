package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin // 允许该controller中的所有请求被其他域访问
public class CorsController {

    @GetMapping("cors")
    public ResponseEntity<String> cors() {
        return new ResponseEntity<>("cors success", HttpStatus.OK);
    }
}
