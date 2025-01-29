package com.example.task_management.controller;

import com.example.task_management.model.User;
import com.example.task_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create a new user.
     * @param user The user details to create.
     * @return The created user.
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Get user by id.
     * @param user_id The ID of the user to retrieve.
     * @return The user with the given ID.
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long user_id) {
        return userService.getUserById(user_id);
    }

    /**
     * Get all users.
     * @return A list of all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Update an existing user.
     * @param user_id The ID of the user to update.
     * @param updatedUser The updated user details.
     * @return The updated user.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long user_id, @RequestBody User updatedUser) {
        return userService.updateUser(user_id, updatedUser);
    }

    /**
     * Delete a user by id.
     * @param user_id The ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
    }
}
