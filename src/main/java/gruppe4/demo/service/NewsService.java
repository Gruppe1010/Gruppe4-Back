package gruppe4.demo.service;

import gruppe4.demo.models.News;
import gruppe4.demo.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;


    /**
     * Vi scraper alle siderne som har ranking
     *
     * */
    public Set<News> scrapeAllNewsPages(){

        HTMLScraper htmlScraper = new HTMLScraper();

        // vi scraper forsiden
        Set<News> news = htmlScraper.scrape("https://news.ycombinator.com/");

        String url = "https://news.ycombinator.com/news?p=";

        // + den kan højest gå til i < 25 men så er den super langsom
        // vi scraper side 2
        for (int i = 2; i < 3; i++) {
            Set<News> newNewsSet = htmlScraper.scrape(url + i);

            news.addAll(newNewsSet);
        }

        return news;
    }

    /***/
    public void updateNewsInDb(Set<News> news){

        newsRepository.saveAll(news);
    }


    public ResponseEntity<List<News>> findAll(){

        // https://stackoverflow.com/questions/25486583/how-to-use-orderby-with-findall-in-spring-data
        // findAllByOrderByRankingAsc() det er en JPA-metode
        //      - vi sætter vores eget kolonnenavn ind
        //      - eller man kan sige findTop10 - 10 er et vilkårligt tal
        //      - eller man kan sige Desc i stedet for Asc
        List<News> news = newsRepository.findAllByOrderByRankingAsc();

        // List<News> news = newsRepository.findAllByOrderByRankingDesc();


        // hvis der IKKE er noget på listen
        if(news.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        // TODO slet test-print
        for(News n : news){
            System.out.println(n.getRanking());
        }

        // ellers ER der noget på listen, og så bliver den returneret
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}














