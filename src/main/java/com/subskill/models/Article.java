package com.subskill.models;

import com.subskill.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @Column(name = "articlename", nullable = false)
    private String articleName;

    @Column(name = "textofarticle", nullable = false)
    private String textOfArticle;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "microskill_id")
    private MicroSkill microSkill;

    public static Article of(ArticleDto articleDto) {
        Article article = new Article();
        article.articleName = articleDto.articleName();
        article.textOfArticle = articleDto.textOfArticle();
        article.microSkill = articleDto.microSkill();
        return article;
    }

    public ArticleDto build() {
        return new ArticleDto(articleName, textOfArticle, microSkill);
    }

}