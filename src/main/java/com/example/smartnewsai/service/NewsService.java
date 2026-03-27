package com.example.smartnewsai.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smartnewsai.model.FlashCard;
import com.example.smartnewsai.model.News;

@Service
public class NewsService {

    // Temporary hardcoded news list (later your teammate can move this to NewsDataLoader)
    private List<News> newsList = Arrays.asList(
            new News(1, "India launches new satellite mission",
                    "India successfully launched a new satellite to improve communication and weather monitoring across the country."),
            new News(2, "Stock market rises sharply",
                    "The stock market rose sharply today due to positive global cues and strong investor confidence."),
            new News(3, "New education policy announced",
                    "The government announced a new education policy focusing on skill development, digital learning, and practical training."),
            new News(4, "AI is changing healthcare",
                    "Artificial Intelligence is being used in hospitals to detect diseases early and help doctors make better decisions."),
            new News(5, "Climate change affects monsoon",
                    "Experts say climate change is impacting monsoon patterns, leading to unpredictable rainfall and extreme weather events.")
    );

    public List<News> getAllNews() {
        return newsList;
    }

    public String summarizeNews(int id) {
        News news = getNewsById(id);
        if (news == null) return "News not found.";

        String content = news.getContent();
        if (content.length() <= 60) return content;

        return content.substring(0, 60) + "...";
    }

    public String explainSimply(int id) {
        News news = getNewsById(id);
        if (news == null) return "News not found.";

        return "In simple words: " + news.getContent();
    }

    public List<FlashCard> generateFlashCards(int id) {
        News news = getNewsById(id);
        if (news == null) return new ArrayList<>();

        List<FlashCard> cards = new ArrayList<>();

        cards.add(new FlashCard("Main Topic", news.getTitle()));
        cards.add(new FlashCard("Key Point", summarizeNews(id)));
        cards.add(new FlashCard("Why it matters", "This news can affect people, economy, or society."));

        return cards;
    }

    public String answerQuestion(int id, String question) {
        News news = getNewsById(id);
        if (news == null) return "News not found.";

        if (question == null || question.trim().isEmpty()) {
            return "Please ask a valid question.";
        }

        // Basic AI-like answer (rule-based)
        if (question.toLowerCase().contains("why")) {
            return "This is important because it impacts society and future decisions.";
        } else if (question.toLowerCase().contains("what")) {
            return "This news is about: " + news.getTitle();
        } else if (question.toLowerCase().contains("how")) {
            return "It may affect people through policy changes, economy, or daily life.";
        }

        return "Based on the news: " + summarizeNews(id);
    }

    private News getNewsById(int id) {
        for (News n : newsList) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }
}