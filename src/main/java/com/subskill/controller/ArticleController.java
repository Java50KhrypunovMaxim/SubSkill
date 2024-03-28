package com.subskill.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.subskill.dto.ArticleDto;
import com.subskill.service.ArticleService;

import static com.subskill.api.ValidationConstants.*;

@Validated
@RestController
@RequestMapping("api/v1/articles")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class ArticleController {

    private final ArticleService articlesService;

    @Operation(summary = "Adding new Article for MicroSkill")
    @PostMapping("/add")
    ArticleDto addArticle(@RequestBody ArticleDto articleDto) {
        log.debug("register article: received article data: {}", articleDto);
        return articlesService.addArticle(articleDto);
    }

    @Operation(summary = "update an Article for MicroSkill")
    @PutMapping("/update/{articleName}")
    ArticleDto updateArticle(@RequestBody ArticleDto articleDto,@PathVariable String articleName
    ) {
        log.debug("update article: received new information about article: " );
        return articlesService.updateArticle(articleDto,articleName);
    }

    @Operation(summary = "delete an Article for MicroSkill")
    @DeleteMapping("/{nameArticle}")
    void deleteArticle(@PathVariable @NotEmpty(message = MISSING_NAME_OF_ARTICLE) String nameArticle) {
        log.debug("delete article: article with name {}", nameArticle);
        articlesService.deleteArticle(nameArticle);
    }

    @Operation(summary = "List of Articles in MicroSkill")
    @GetMapping("/all")
    List<ArticleDto> listOfArticles() {
        log.debug("List of articles have been received");
        return articlesService.allArticles();
    }
}