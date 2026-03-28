package com.eventhub.controller;

import com.eventhub.dto.ChatRequest;
import com.eventhub.dto.EventResponse;
import com.eventhub.service.GeminiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final GeminiService geminiService;

    public AiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("status", "UP", "service", "Gemini AI");
    }

    @PostMapping("/chat")
    public Map<String, Object> chat(@RequestBody ChatRequest req) {

        String result = geminiService.chat(req.message());

        return Map.of(
                "success", true,
                "question", req.message(),
                "answer", result
        );
    }

    @PostMapping("/event/description")
    public Map<String, Object> generateDescription(@RequestBody EventRequest req) {

        String prompt = """
                Generate a professional event description:

                Name: %s
                Category: %s
                Location: %s
                Date: %s
                Keywords: %s
                """.formatted(req.name(), req.category(), req.location(), req.date(), req.keywords());

        return Map.of(
                "success", true,
                "description", geminiService.chat(prompt)
        );
    }

    @PostMapping("/event/structured")
    public EventResponse structured(@RequestBody EventRequest req) {
        return geminiService.generateStructuredEvent(req);
    }

    @PostMapping("/event/tags")
    public Map<String, Object> tags(@RequestBody EventRequest req) {

        String prompt = """
                Generate 5 event tags:
                Name: %s
                Category: %s
                Keywords: %s
                Return comma separated only.
                """.formatted(req.name(), req.category(), req.keywords());

        return Map.of("tags", geminiService.chat(prompt));
    }

    @PostMapping("/event/schedule")
    public Map<String, Object> schedule(@RequestBody ScheduleRequest req) {

        String prompt = """
                Create event schedule:

                Event: %s
                Sessions: %s
                Duration: %d hours
                """.formatted(req.name(), req.sessions(), req.durationHours());

        return Map.of("schedule", geminiService.chat(prompt));
    }

    // DTOs
    public record EventRequest(
            String name,
            String category,
            String location,
            String date,
            String keywords
    ) {}

    public record ScheduleRequest(
            String name,
            String sessions,
            int durationHours
    ) {}

    public record ChatRequest(
            String message
    ) {}
}