package com.subskill.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.subskill.dto.ArticleDto;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Table(name = "articles")
	@Entity
	
	public class Article {
		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id",nullable = false,unique = true)
	    private long id;
	    
	    @Column(name = "articleName",nullable = false)
	    private String articleName;
	    
	    @Column(name = "textOfArticle",nullable = false)
	    private String textOfArticle;
	    
	    @Column(name = "idOfSkills", nullable = false)
	    private long idOfSkills;
	    
	    
	    public static Article of(ArticleDto articleDto) {
	    	Article article = new Article();
	    	article.articleName = articleDto.articleName();
	    	article.textOfArticle = articleDto.textOfArticle();
	    	article.idOfSkills=articleDto.idOfSkills();
	        return article;
	    }

		public ArticleDto build () {
			return new ArticleDto(articleName, textOfArticle, idOfSkills);
		}
		
		public void setTextOfArticle(String textOfArticle) {
			this.textOfArticle = textOfArticle;
		}
		
		public void setArticleName(String articleName) {
			this.articleName = articleName;
		}

}

