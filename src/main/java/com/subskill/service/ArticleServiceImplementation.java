package com.subskill.service;

import java.util.List;
import java.util.stream.Collectors;

import com.subskill.exception.ArticleNotFoundException;
import com.subskill.exception.IllegalArticleStateException;
import com.subskill.models.Article;
import com.subskill.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import com.subskill.dto.ArticleDto;


@Service
@AllArgsConstructor
@Slf4j
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    @Transactional
    public ArticleDto addArticle(ArticleDto articleDto) {
        if (articleRepository.existsByArticleName(articleDto.articleName())) {
            throw new IllegalArticleStateException();
        }
        Article article = Article.of(articleDto);
        articleRepository.save(article);
        log.debug("Article {} has been saved", articleDto);
        return articleDto;
    }

    @Override
    @Transactional
    public ArticleDto updateArticle(ArticleDto articleDto) {
        Article article = articleRepository.findByArticleName(articleDto.articleName()).orElseThrow(ArticleNotFoundException::new);
        article.setTextOfArticle(articleDto.textOfArticle());
        article.setArticleName(articleDto.articleName());
        article.setMicroSkill(articleDto.idOfSkills());
        log.debug("Article {} has been update", articleDto);
        return articleDto;
    }

    @Override
    @Transactional
    public void deleteArticle(String articleName) {
        Article article = articleRepository.findByArticleName(articleName).orElseThrow(ArticleNotFoundException::new);
        ArticleDto res = article.build();
        articleRepository.deleteById(article.getId());
        log.debug("article with name {} has been deleted", res.articleName());

    }

    @Override
    public List<String> allArticles() {
        List<Article> articles = articleRepository.findAll();
        List<String> articleNames = articles.stream()
                .map(Article::getArticleName)
                .collect(Collectors.toList());
        log.debug("All articles {}", articleNames);

        return articleNames;
    }


}
