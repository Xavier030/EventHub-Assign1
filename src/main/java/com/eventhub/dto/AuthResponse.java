package com.eventhub.dto;

/**
 * Data Transfer Object for authentication responses.
 * Contains the JWT token returned to the client after successful login.
 */
public record AuthResponse(String token) {}