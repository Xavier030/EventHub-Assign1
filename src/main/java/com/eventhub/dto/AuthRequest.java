package com.eventhub.dto;

/**
 * DTO for authentication requests, now includes email.
 */
public record AuthRequest(String username, String email, String password) {}