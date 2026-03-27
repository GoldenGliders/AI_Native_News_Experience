package com.example.smartnewsai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.smartnewsai.model.FlashCard;
import com.example.smartnewsai.model.News;
import com.example.smartnewsai.service.NewsService;

@RestController
@RequestMapping("/api")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // Get all news
    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    // Summarize
    @GetMapping("/summarize/{id}")
    public String summarize(@PathVariable int id) {
        return newsService.summarizeNews(id);
    }

    // Explain simply
    @GetMapping("/explain/{id}")
    public String explain(@PathVariable int id) {
        return newsService.explainSimply(id);
    }

    // Flashcards
    @GetMapping("/flashcards/{id}")
    public List<FlashCard> flashCards(@PathVariable int id) {
        return newsService.generateFlashCards(id);
    }

    @GetMapping("/ask/{id}")
    public String askQuestion(@PathVariable int id, @RequestParam String question) {
        return newsService.answerQuestion(id, question);
    }
}