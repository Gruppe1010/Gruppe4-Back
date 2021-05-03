package gruppe4.demo.service;

import gruppe4.demo.models.News;
import gruppe4.demo.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class NewsService {

    /**
     * Vi scraper alle siderne som har ranking
     *
     * */
    public Set<News> scrapeAllNewsPages(){

        HTMLScraper htmlScraper = new HTMLScraper();

        Set<News> news = htmlScraper.scrape("https://news.ycombinator.com/");

        String url = "https://news.ycombinator.com/news?p=";

        // + den kan højest gå til i < 25 men så er den super langsom
        for (int i = 2; i < 3; i++) {
            Set<News> newNewsSet = htmlScraper.scrape(url + i);

            news.addAll(newNewsSet);
        }

        return news;
    }




}
