package com.system.restfulservice.controller;

import com.system.restfulservice.domain.User;
import com.system.restfulservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class UserJPAController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println(userService.countUser());
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(linTo.withRel("all-users"));
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User saveUser = userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
