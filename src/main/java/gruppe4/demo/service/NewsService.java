package gruppe4.demo.service;

import gruppe4.demo.models.News;
import gruppe4.demo.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

        Set<News> news = htmlScraper.scrape("https://news.ycombinator.com/");

        String url = "https://news.ycombinator.com/news?p=";

        // + den kan højest gå til i < 25 men så er den super langsom
        for (int i = 2; i < 3; i++) {
            Set<News> newNewsSet = htmlScraper.scrape(url + i);

            news.addAll(newNewsSet);
        }

        return news;
    }

    /***/
    public void updateNewsInDb(Set<News> news){

        for(News n : news){

            Optional<News> optionalNews = newsRepository.findById(n.getId());

            // hvis den har fundet en news med samme id
            if(optionalNews.isPresent()){

                // tjek om de er HELT ens
                News foundNews = optionalNews.get();

                // hvis de IKKE er HELT ens, skal den opdateres
                if(!n.equals(foundNews)){
                    newsRepository.updateNews(n);
                }
            } else{ // hvis den IKKE er fundet i db, skal den indsætte
                newsRepository.save(n);
            }
        }
    }


    // ! Arbejd her
    public ResponseEntity<Set<News>> findAll(){

        Set<News>

        new ResponseEntity<>(, HttpStatus);

        // enten Http.OK ELLER Http.None_found eller sådan noget

        newsRepository.findAll();




    }


}














