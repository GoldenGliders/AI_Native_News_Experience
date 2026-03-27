package com.example.smartnewsai.service;

import com.example.smartnewsai.model.News;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsDataLoader {

    public List<News> getNews() {

        List<News> newsList = new ArrayList<>();

        newsList.add(new News(
                "Startup Funding Boom",
                "Indian startups raised $2B this quarter",
                "Startup",
                "2026-01-10"
        ));

        newsList.add(new News(
                "Stock Market Rally",
                "Sensex closed 500 points higher",
                "Market",
                "2026-01-11"
        ));

        return newsList;
    }
}