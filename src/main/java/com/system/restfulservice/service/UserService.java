package com.system.restfulservice.service;

import com.system.restfulservice.domain.User;
import com.system.restfulservice.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private static final List<User> users = new ArrayList<>();

    private static long usersCount = 3L;

    static {
        users.add(new User(1L, "Sang", new Date()));
        users.add(new User(2L, "Min", new Date()));
        users.add(new User(3L, "Bong", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        if (user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }
        users.add(user);
        return user;
    }

    public User findOne(long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Not Found - " + id));
    }


}
