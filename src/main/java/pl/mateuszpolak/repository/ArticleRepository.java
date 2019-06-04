package pl.mateuszpolak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszpolak.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
