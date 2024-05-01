package com.subskill.models;

import com.subskill.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "article_name", nullable = false)
    private String articleName;

    @Column(name = "text", nullable = false)
    private String textOfArticle;

    @Column(name = "microskill_id")
    private Long microSkill;

    public static Article of(ArticleDto articleDto) {
        Article article = new Article();
        article.id = articleDto.id();
        article.articleName = articleDto.articleName();
        article.textOfArticle = articleDto.textOfArticle();
        article.microSkill = articleDto.microskillId();
        return article;
    }

    public ArticleDto build() {
        return new ArticleDto(id,articleName, textOfArticle, microSkill);
    }


}