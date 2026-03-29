package com.eventhub.service;

import org.springframework.stereotype.Service;

/**
 * Service layer for FAQ RAG logic
 * In real projects, this would connect to:
 * - Vector Database (Pinecone, Weaviate)
 * - Embedding model
 * - LLM (Gemini / OpenAI)
 */
@Service
public class FaqService {

    /**
     * Mock RAG answer generation
     * @param question user question
     * @return answer string
     */
    public String ragAnswer(String question) {

        // Validate input
        if (question == null || question.isEmpty()) {
            return null;
        }

        String q = question.toLowerCase();

        // Simple keyword-based matching (mock semantic search)
        if (q.contains("refund")) {
            return "Refund allowed within 48 hours.";
        }

        if (q.contains("register")) {
            return "Go to event page and click register.";
        }

        if (q.contains("parking")) {
            return "Yes, free parking available.";
        }

        if (q.contains("ticket")) {
            return "Tickets can be transferred to another user.";
        }

        if (q.contains("food")) {
            return "Snacks and drinks are included.";
        }

        // Default fallback
        return "No relevant answer found.";
    }
}