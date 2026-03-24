package com.eventhub.controller;

import com.eventhub.dto.AuthRequest;
import com.eventhub.dto.AuthResponse;
import com.eventhub.dto.ResetPasswordRequest;
import com.eventhub.dto.ResetRequest;
import com.eventhub.entity.Role;
import com.eventhub.entity.User;
import com.eventhub.service.JwtService;
import com.eventhub.service.PasswordResetService;
import com.eventhub.service.RoleService;
import com.eventhub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.HashSet;

/**
 * Controller for handling authentication-related endpoints.
 * Provides user registration and login functionality with JWT.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetService passwordResetService;

    public AuthController(UserService userService,
                          RoleService roleService,
                          JwtService jwtService,
                          PasswordEncoder passwordEncoder,
                          PasswordResetService passwordResetService) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetService = passwordResetService;
    }

    @GetMapping("/test/password")
    public String test() {
        return new BCryptPasswordEncoder().encode("123456");
    }
    /**
     * Login endpoint.
     * Validates username and password and returns a JWT token if successful.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = userService.findByUsername(request.username());
        if (user == null || !passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     * Registration endpoint.
     * Creates a new user with ROLE_USER and hashed password.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        // Check if username already exists
        if (userService.findByUsername(request.username()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        // Assign ROLE_USER
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName("ROLE_USER")); // Make sure this role exists in DB
        user.setRoles(roles);

        // Save user
        userService.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/request-reset")
    public ResponseEntity<?> requestReset(@RequestBody ResetRequest request) {
        String token = passwordResetService.createResetToken(request.email());

        return ResponseEntity.ok("Reset token: " + token);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordResetService.resetPassword(request.token(), request.newPassword());
        return ResponseEntity.ok("Password reset successful");
    }
}