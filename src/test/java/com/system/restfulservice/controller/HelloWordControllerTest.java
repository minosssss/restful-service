package com.system.restfulservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(HelloWordController.class)
public class HelloWordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void HelloWorld_returnsHelloWorld() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    public void HelloWorldBean_returnsHelloWorldBean() throws Exception {
        this.mockMvc.perform(get("/hello-bean"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("hello-world"));
    }

    @Test
    public void HelloWorldBeanGetName_returnsCustomMessage() throws Exception {
        String name = "TestName";
        this.mockMvc.perform(get("/hello-bean/path/{name}", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, TestName!"));
    }
}
