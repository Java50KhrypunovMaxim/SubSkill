package com.subskill.service.impl;

import java.util.List;

import com.subskill.exception.ArticleNotFoundException;
import com.subskill.exception.IllegalArticleStateException;
import com.subskill.models.Article;
import com.subskill.repository.ArticleRepository;
import com.subskill.service.ArticleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import com.subskill.dto.ArticleDto;


@Service
@AllArgsConstructor
@Slf4j
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ModelMapper modelMapper;

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
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(articleDto, article);
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
