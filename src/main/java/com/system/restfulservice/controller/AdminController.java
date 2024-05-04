package com.system.restfulservice.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.system.restfulservice.domain.Admin;
import com.system.restfulservice.domain.AdminV2;
import com.system.restfulservice.domain.User;
import com.system.restfulservice.exception.UserNotFoundException;
import com.system.restfulservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue getUserForAdmin(@PathVariable Long id) {
        User user = userService.findOne(id);
        Admin adminUser = new Admin();
        if (user == null) {
            throw new UserNotFoundException(String.format("Not Found - %s" , id));
        } else {
            BeanUtils.copyProperties(user, adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        SimpleFilterProvider userFilters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(userFilters);
        return mapping;
    }

    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue getUserForAdminV2(@PathVariable Long id) {
        User user = userService.findOne(id);
        AdminV2 adminUser = new AdminV2();
        if (user == null) {
            throw new UserNotFoundException(String.format("Not Found - %s" , id));
        } else {
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        SimpleFilterProvider userFilters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(userFilters);
        return mapping;
    }

    @GetMapping("/users")
    public MappingJacksonValue getAllUsersForAdmin() {
        List<User> users = userService.findAll();
        List<Admin> adminUsers = new ArrayList<>();

        for (User user : users) {
            Admin admin = new Admin();
            BeanUtils.copyProperties(user, admin);
            adminUsers.add(admin);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        SimpleFilterProvider userFilters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(userFilters);
        return mapping;
    }
}
