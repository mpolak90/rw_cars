package pl.mateuszpolak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszpolak.model.Article;
import pl.mateuszpolak.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article findOne(Long id) {
        return articleRepository.findOne(id);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }
}