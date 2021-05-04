package gruppe4.demo.controllers;

import gruppe4.demo.models.News;
import gruppe4.demo.repositories.NewsRepository;
import gruppe4.demo.service.HTMLScraper;
import gruppe4.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(value = "*")
public class NewsRESTController {

    // TODO ranking - der kommer til at ligge mange med samme ranking (SIDSTE ranking) i db


    @Autowired
    NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<List<News>> index() {

        // scrapedNews-Set indeholder alle de nyoprettede News-obj ud fra scrapet
        Set<News> scrapedNews = newsService.scrapeAllNewsPages();

        // vi opdaterer dem // TODO test at denne virker
        newsService.updateNewsInDb(scrapedNews);

        return newsService.findAll();
    }










}
