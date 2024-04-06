package com.system.restfulservice.controller;

import com.system.restfulservice.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    @GetMapping("/hello")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-bean")
    public HelloWorldBean HelloWorldBean() {
        return new HelloWorldBean("hello-world");
    }

    @GetMapping("/hello-bean/path/{name}")
    public HelloWorldBean HelloWorldBeanGetName(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello, %s!", name));
    }
}
