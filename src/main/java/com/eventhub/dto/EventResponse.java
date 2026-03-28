package com.eventhub.dto;

import java.util.List;

public record EventResponse(
        String title,
        String description,
        List<String> highlights,
        String targetAudience,
        int estimatedAttendance
) {}