package com.system.restfulservice.service;

import com.system.restfulservice.domain.User;
import com.system.restfulservice.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Initialize your test environment here
        // For simplicity, we're assuming the UserService class is stateful as per your initial setup
        // In a real application, you might want to mock the data repository or use a test database
    }

    @Test
    public void whenFindAll_thenReturnAllUsers() {
        List<User> users = userService.findAll();
        assertFalse(users.isEmpty(), "Expected non-empty list of users");
    }

    @Test
    public void whenSaveUser_thenCorrectlyIncreaseUserCount() {
        long initialCount = userService.findAll().size();
        userService.save(new User(null, "New User", new Date()));
        assertEquals(initialCount + 1, userService.findAll().size(), "User count did not increase after saving a new user");
    }

    @Test
    public void whenFindOneWithValidId_thenReturnUser() {
        User user = userService.findOne(1L); // Assuming an ID known to exist
        assertNotNull(user, "User should not be null when queried with a valid ID");
    }

    @Test
    public void whenFindOneWithInvalidId_thenThrowException() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.findOne(999L);
        });

        String expectedMessage = "id-999";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Exception message does not contain the expected ID");
    }

    @Test
    void findOne_whenUserDoesNotExist_thenThrowException() {
        long nonexistentUserId = 999L; // Assuming this ID does not exist in your static list
        assertThrows(UserNotFoundException.class, () -> userService.findOne(nonexistentUserId));
    }
}