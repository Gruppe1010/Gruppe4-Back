package gruppe4.demo.repositories;

import gruppe4.demo.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {


    List<News> findAllByOrderByRankingAsc();

}
