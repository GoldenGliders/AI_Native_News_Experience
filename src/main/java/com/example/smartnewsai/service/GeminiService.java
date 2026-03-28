package com.example.smartnewsai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {

    @Value("AIzaSyBJV_VRpse6lOP1dRqspYvlLKEyp-iMFp0")
    private String apiKey;

    private final WebClient webClient = WebClient.create();

    public String generateText(String prompt) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey;

        Map<String, Object> body = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        try {
            Map response = webClient.post()
                    .uri(url)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            // Extract AI output
            var candidates = (java.util.List<Map>) response.get("candidates");
            Map firstCandidate = candidates.get(0);

            Map content = (Map) firstCandidate.get("content");
            var parts = (java.util.List<Map>) content.get("parts");

            return parts.get(0).get("text").toString();

        } catch (Exception e) {
            return "AI Error: " + e.getMessage();
        }
    }
}