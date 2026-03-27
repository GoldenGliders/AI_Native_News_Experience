package com.example.smartnewsai.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smartnewsai.model.FlashCard;
import com.example.smartnewsai.model.News;

@Service
public class NewsService {

    private List<News> newsList = Arrays.asList(
            new News(
                    "India launches new satellite mission",
                    "India successfully launched a new satellite to improve communication and weather monitoring across the country.",
                    "Science",
                    "26-03-2026"
            ),
            new News(
                    "Stock market rises sharply",
                    "The stock market rose sharply today due to positive global cues and strong investor confidence.",
                    "Business",
                    "26-03-2026"
            ),
            new News(
                    "New education policy announced",
                    "The government announced a new education policy focusing on skill development, digital learning, and practical training.",
                    "Education",
                    "26-03-2026"
            ),
            new News(
                    "AI is changing healthcare",
                    "Artificial Intelligence is being used in hospitals to detect diseases early and help doctors make better decisions.",
                    "Technology",
                    "26-03-2026"
            ),
            new News(
                    "Climate change affects monsoon",
                    "Experts say climate change is impacting monsoon patterns, leading to unpredictable rainfall and extreme weather events.",
                    "Environment",
                    "26-03-2026"
            )
    );

    public List<News> getAllNews() {
        return newsList;
    }

    public String summarizeNews(int index) {
        if (index < 0 || index >= newsList.size()) {
            return "News not found.";
        }

        String content = newsList.get(index).getContent();

        if (content.length() <= 60) {
            return content;
        }

        return content.substring(0, 60) + "...";
    }

    public String explainSimply(int index) {
        if (index < 0 || index >= newsList.size()) {
            return "News not found.";
        }

        return "In simple words: " + newsList.get(index).getContent();
    }

    public List<FlashCard> generateFlashCards(int index) {
        if (index < 0 || index >= newsList.size()) {
            return new ArrayList<>();
        }

        News news = newsList.get(index);

        List<FlashCard> cards = new ArrayList<>();

        cards.add(new FlashCard("Topic", news.getTitle()));
        cards.add(new FlashCard("Category", news.getCategory()));
        cards.add(new FlashCard("Summary", summarizeNews(index)));
        cards.add(new FlashCard("Why it matters", "This news can affect people and society."));

        return cards;
    }

    public String answerQuestion(int index, String question) {
        if (index < 0 || index >= newsList.size()) {
            return "News not found.";
        }

        if (question == null || question.trim().isEmpty()) {
            return "Please ask a valid question.";
        }

        question = question.toLowerCase();

        if (question.contains("what")) {
            return "This news is about: " + newsList.get(index).getTitle();
        }
        if (question.contains("why")) {
            return "This matters because it may impact people, economy, or future decisions.";
        }
        if (question.contains("when")) {
            return "This news date is: " + newsList.get(index).getDate();
        }
        if (question.contains("category")) {
            return "This news belongs to: " + newsList.get(index).getCategory();
        }

        return "Based on the news: " + summarizeNews(index);
    }
}