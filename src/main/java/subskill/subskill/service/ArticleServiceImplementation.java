package subskill.subskill.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import subskill.subskill.dto.ArticleDto;
import subskill.subskill.dto.UserDto;
import subskill.subskill.exception.ArticleNotFoundException;
import subskill.subskill.exception.IllegalArticleStateException;
import subskill.subskill.models.Article;
import subskill.subskill.models.User;
import subskill.subskill.repository.ArticleRepository;
import subskill.subskill.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j

public class ArticleServiceImplementation implements ArticleService {

	private final ArticleRepository articleRepository;

	@Override
	@Transactional
	public ArticleDto addArticle(ArticleDto articleDto) {
	    if(articleRepository.existsByArticleName(articleDto.articleName())) {
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
		Article article = articleRepository.findByName(articleDto.articleName()).orElseThrow(() -> new ArticleNotFoundException());
		article.setTextOfArticle(articleDto.textOfArticle());
		article.setArticleName(articleDto. articleName());
		log.debug("Article {} has been update", articleDto);
		return articleDto;
	}

	@Override
	@Transactional
	public ArticleDto deleteArticle(long ID) {
		Article article = articleRepository.findById(ID).orElseThrow(() -> new ArticleNotFoundException());
		ArticleDto res = article.build();
		articleRepository.deleteById(ID);
		log.debug("article ID {} has been deleted", ID);
		return res;
	}

	@Override
	public List<String> allArticles() {
		List<Article> articles = articleRepository.findAll();
	    List<String> articleNames = articles.stream()
	            .map(Article::getArticleName)
	            .collect(Collectors.toList());
	    log.debug("All articles {}",articleNames );
	    return articleNames;
	}
	

	
	


}
