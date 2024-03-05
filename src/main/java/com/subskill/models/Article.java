package com.subskill.models;

import com.subskill.dto.ArticleDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.subskill.api.ValidationConstants.MISSING_ID_OF_SKILLS;

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
    private @NotNull(message = MISSING_ID_OF_SKILLS) Long microSkill;

    public static Article of(ArticleDto articleDto) {
        Article article = new Article();
        article.articleName = articleDto.articleName();
        article.textOfArticle = articleDto.textOfArticle();
        article.microSkill = articleDto.microskillId();
        return article;
    }

    public ArticleDto build() {
        return new ArticleDto(articleName, textOfArticle, microSkill);
    }

}