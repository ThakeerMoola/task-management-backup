package com.example.task_management.service;

import com.example.task_management.model.User;
import com.example.task_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Create a new user with unique username and email.
     * @param user The user details to create.
     * @return The created user.
     * @throws IllegalArgumentException if the username or email already exists.
     */
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Get user by ID.
     * @param user_id The ID of the user to retrieve.
     * @return The found user.
     * @throws RuntimeException if the user is not found.
     */
    public User getUserById(Long user_id) {
        return userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Get all users.
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Update an existing user.
     * @param user_id The ID of the user to update.
     * @param updatedUser The updated user details.
     * @return The updated user.
     */
    public User updateUser(Long user_id, User updatedUser) {
        User existingUser = getUserById(user_id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail()); // Update email

        // Only update password if it's not null or empty
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        existingUser.setRole(updatedUser.getRole());
        return userRepository.save(existingUser);
    }

    /**
     * Delete a user by ID.
     * @param user_id The ID of the user to delete.
     */
    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }
}
