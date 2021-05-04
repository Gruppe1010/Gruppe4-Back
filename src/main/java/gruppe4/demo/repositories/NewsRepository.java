package gruppe4.demo.repositories;

import gruppe4.demo.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// https://www.javaguides.net/2019/07/spring-data-jpa-save-findbyid-findall-deletebyid-example.html

public interface NewsRepository extends JpaRepository<News, Integer> {

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
    List<News> findAllByOrderByRankingAsc();

}
