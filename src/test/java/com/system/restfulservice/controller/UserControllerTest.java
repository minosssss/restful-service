package com.system.restfulservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.restfulservice.domain.User;
import com.system.restfulservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    public void getAllUsers_whenGetMethod() throws Exception {
        User user1 = new User(1L, "Test1", new Date(), "1234" , "111111-12345678");
        User user2 = new User(2L, "Test2", new Date(), "1234" , "111111-12345678");
        List<User> userList = Arrays.asList(user1, user2);

        given(userService.findAll()).willReturn(userList);

        mockMvc.perform(get("/users")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(userList.size()));
    }

    @Test
    public void findUser_whenGetMethod() throws Exception {
        User user = new User(1L, "Test", new Date(), "1234" , "111111-12345678");

        given(userService.findOne(anyLong())).willReturn(user);

        mockMvc.perform(get("/users/{id}", user.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()));
    }

    @Test
    public void createUser_whenPostMethod() throws Exception {
        User user = new User(null, "Test", new Date(), "1234" , "111111-12345678");
        User savedUser = new User(1L, "Test", new Date(), "1234" , "111111-12345678");

        given(userService.save(any(User.class))).willReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(savedUser.getId()))
//                .andExpect(jsonPath("$.name").value(savedUser.getName()))
        // 다른 필드도 여기에 추가할 수 있습니다. 예: .andExpect(jsonPath("$.joinDate").value(savedUser.getJoinDate()))
        ;
    }
}
