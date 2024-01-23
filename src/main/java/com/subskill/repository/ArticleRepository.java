package com.subskill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subskill.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    Optional<Article> findById(Long id);
    Optional<Article> findByName(String name);
    boolean existsByArticleName(String name);
}
