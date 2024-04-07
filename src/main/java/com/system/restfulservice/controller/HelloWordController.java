package com.system.restfulservice.controller;

import com.system.restfulservice.bean.HelloWorldBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class HelloWordController {
    private final MessageSource messageSource;
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

    @GetMapping("/hello/internationalized")
    public String HelloWorldInternationalized(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
