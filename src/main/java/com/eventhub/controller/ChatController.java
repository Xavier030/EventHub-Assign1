package com.eventhub.controller;

import com.eventhub.service.GeminiService;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final GeminiService geminiService;

    public ChatController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    /**
     * Public chat endpoint (NO JWT required)
     */
    @PostMapping
    public Map<String, Object> chat(@RequestBody Map<String, String> body) {

        String message = body.get("message");

        String result = geminiService.chat(message);

        return Map.of(
                "success", true,
                "response", result
        );
    }
}