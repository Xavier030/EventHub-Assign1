package com.eventhub.controller;

import com.eventhub.service.FaqService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/faq")
public class FaqController {

    // In-memory FAQ store (mock database)
    private final List<Map<String, Object>> faqStore = new ArrayList<>();

    // Inject FaqService (business logic layer)
    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    /**
     * 1. Load FAQ data (Mock Vector Database)
     * This simulates loading FAQs into a vector store
     */
    @PostMapping("/load")
    public Map<String, Object> loadFaqs() {

        faqStore.clear();

        faqStore.add(createFaq(1, "registration", "How to register?", "Go to event page and click register."));
        faqStore.add(createFaq(2, "refund", "Can I get a refund?", "Refund allowed within 48 hours."));
        faqStore.add(createFaq(3, "venue", "Is parking available?", "Yes, free parking available."));
        faqStore.add(createFaq(4, "schedule", "Where is agenda?", "Agenda is listed on event page."));
        faqStore.add(createFaq(5, "dress", "Dress code?", "Casual or business casual."));
        faqStore.add(createFaq(6, "support", "Contact support?", "Email support@eventhub.com"));
        faqStore.add(createFaq(7, "tickets", "Ticket transfer?", "Yes, allowed."));
        faqStore.add(createFaq(8, "food", "Food included?", "Snacks and drinks included."));
        faqStore.add(createFaq(9, "accessibility", "Wheelchair access?", "Fully accessible."));
        faqStore.add(createFaq(10, "networking", "Networking sessions?", "Yes after main event."));

        return Map.of(
                "success", true,
                "count", faqStore.size()
        );
    }

    /**
     * 2. Search FAQs (Mock semantic search)
     */
    @PostMapping("/search")
    public Map<String, Object> search(@RequestBody Map<String, Object> req) {

        String query = (String) req.get("query");

        // Prevent NullPointerException
        if (query == null || query.isEmpty()) {
            return Map.of(
                    "success", false,
                    "message", "Query cannot be empty"
            );
        }

        List<Map<String, Object>> results = faqStore.stream()
                .filter(f -> f.get("question").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        return Map.of(
                "success", true,
                "query", query,
                "results", results
        );
    }

    /**
     * 3. RAG Q&A (Mock AI + Retrieval)
     */
    @PostMapping("/rag")
    public Map<String, Object> rag(@RequestBody Map<String, String> req) {

        String question = req.get("question");

        // Validate input
        if (question == null || question.isEmpty()) {
            return Map.of(
                    "success", false,
                    "message", "Question cannot be empty"
            );
        }

        // Call service layer
        String answer = faqService.ragAnswer(question);

        // Use HashMap to avoid null crash (Map.of does not allow null)
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("question", question);
        result.put("answer", answer != null ? answer : "No relevant answer found");

        return result;
    }

    /**
     * Helper method to create FAQ entry
     */
    private Map<String, Object> createFaq(int id, String category, String q, String a) {
        return Map.of(
                "id", id,
                "category", category,
                "question", q,
                "answer", a
        );
    }
}