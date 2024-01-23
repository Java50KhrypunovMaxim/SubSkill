package subskill.subskill.service;

import java.util.List;

import subskill.subskill.dto.ArticleDto;
import subskill.subskill.dto.UserDto;
import subskill.subskill.models.User;

public interface ArticleService {
	ArticleDto addArticle(ArticleDto articleDto);
	ArticleDto updateArticle(ArticleDto articleDto);
	ArticleDto deleteArticle(long ID);
	List<String> allArticles();

}
