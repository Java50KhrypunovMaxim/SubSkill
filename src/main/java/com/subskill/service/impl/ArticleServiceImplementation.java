package com.subskill.service.impl;

import com.subskill.dto.ArticleDto;
import com.subskill.exception.ArticleNotFoundException;
import com.subskill.exception.IllegalArticleStateException;
import com.subskill.models.Article;
import com.subskill.repository.ArticleRepository;
import com.subskill.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


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
    public ArticleDto updateArticle(ArticleDto articleDto,String articleName) {
        Optional<Article> optionalArticle = articleRepository.findByarticleName(articleName);
        if (optionalArticle.isPresent()) {
            Article article1 = optionalArticle.get();
//            if (articleDto.microskillId() != 0L) {
//                article1.setMicroSkill(articleDto.microskillId());
//            }
//            if (articleDto.textOfArticle() != null) {
//                article1.setTextOfArticle(articleDto.textOfArticle());
//            }
//            if (articleDto.articleName() != null) {
//                article1.setArticleName(articleDto.articleName());
//            }
            articleRepository.save(article1);
            log.debug("Article has been updated");
            return article1.build();
        } else {
            throw new ArticleNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteArticle(String articleName) {
        Article article = articleRepository.findByarticleName(articleName).orElseThrow(ArticleNotFoundException::new);
        ArticleDto res = article.build();
        articleRepository.deleteById(article.getId());
        log.debug("article with name {} has been deleted", res.articleName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleDto> allArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDto> articlesDto = articles.stream()
                .map(Article::build)
                .toList();
        log.debug("All articles {}", articlesDto);
        return articlesDto;
    }
}
