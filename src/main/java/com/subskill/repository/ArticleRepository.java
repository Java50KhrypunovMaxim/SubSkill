package com.subskill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subskill.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByid(Long id);
    Optional<Article> findByArticleName(String articleName);
    boolean existsByArticleName(String articleName);
}
