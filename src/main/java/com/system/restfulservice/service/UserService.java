package com.system.restfulservice.service;

import com.system.restfulservice.domain.User;
import com.system.restfulservice.exception.UserNotFoundException;
import com.system.restfulservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final List<User> users = new ArrayList<>();

    private static long usersCount = 3L;

    static {
        users.add(new User(1L, "Sang", LocalDate.now(), "1234" , "111111-12345678"));
        users.add(new User(2L, "Min", LocalDate.now(),  "1234" , "111111-12345678"));
        users.add(new User(3L, "Bong", LocalDate.now(), "1234" , "111111-12345678"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        if (user.getJoinDate() == null) {
            user.setJoinDate(LocalDate.now());
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

    public User deleteById(long id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id-"+ id));

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);

    }

    public long countUser() {
        return userRepository.count();
    }


}
