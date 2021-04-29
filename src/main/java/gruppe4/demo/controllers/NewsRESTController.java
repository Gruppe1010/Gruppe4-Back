package gruppe4.demo.controllers;

import gruppe4.demo.models.News;
import gruppe4.demo.repositories.NewsRepository;
import gruppe4.demo.service.HTMLScraper;
import gruppe4.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class NewsRESTController {

    @Autowired
    NewsRepository newsRepository;

    NewsService newsService = new NewsService();

    @GetMapping("/news")
    public ResponseEntity<Set<News>> index() {

        Set<News> news = newsService.scrapeAllNewsPages();



        for(News n : news){

            // hvis der IKKE findes et exact match i db
            // ! HER arbejder vi videre - lav query
            /*
            if(!newsRepository.exactMatch(n)){
                newsRepository.save(n);
            }

             */
            newsRepository.save(n);
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }










}
