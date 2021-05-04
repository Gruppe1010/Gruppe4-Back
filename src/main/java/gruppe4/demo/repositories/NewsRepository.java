package gruppe4.demo.repositories;

import gruppe4.demo.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Integer> {


    // TODO lav query som lægger ned
    // @Transactional
    @Modifying
    @Query() // ! Lav den der update-query
    void updateNews(News news);


    // TODO lav query



}
