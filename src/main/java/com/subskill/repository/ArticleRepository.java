package com.subskill.repository;

import com.subskill.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(Long id);
    Optional<Article> findByarticleName(String name);
    boolean existsByArticleName(String articleName);
}
