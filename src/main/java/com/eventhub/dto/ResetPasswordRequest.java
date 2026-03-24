package com.eventhub.dto;

public record ResetPasswordRequest(String token, String newPassword) {}