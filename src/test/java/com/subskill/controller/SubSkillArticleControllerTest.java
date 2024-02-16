package com.subskill.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.service.MicroSkillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.subskill.dto.ArticleDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.service.ArticleService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ArticleController.class)
public class SubSkillArticleControllerTest {
    @MockBean
    ArticleService articleService;

    @MockBean
    MicroSkillService microSkillService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private static final String ARTICLE_NAME3 = "About Pyton";

    private static final String TEXT1 = "Rambo";
    private static final String TEXT3 = "Vandam";

    Technology technology = new Technology();

    MicroSkillDto microSkillDto1 = new MicroSkillDto("Database Design", "", "", "database_design.jpg", List.of(Tags.BACKEND), 12.0, LocalDateTime.now(),   "About Microskill", Level.ADVANCED, List.of(), 1L);



    ArticleDto ArticleDto1 = new ArticleDto(ARTICLE_NAME3, TEXT3, MicroSkill.of(microSkillDto1));
    ArticleDto UpdateArticleDto = new ArticleDto(ARTICLE_NAME3, TEXT1, MicroSkill.of(microSkillDto1));

    @Test
    void testRegisterArticle() throws Exception {
        when(articleService.addArticle(ArticleDto1)).thenReturn(ArticleDto1);
        String jsonArticleDto = mapper.writeValueAsString(ArticleDto1);
        String actualJSON = mockMvc.perform(post("http://localhost:8080/api/v1/articles").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArticleDto)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(jsonArticleDto, actualJSON);

    }

    @Test
    void testUpdateArticle() throws Exception {
        when(articleService.updateArticle(UpdateArticleDto)).thenReturn(UpdateArticleDto);
        String jsonArticleDtoUpdated = mapper.writeValueAsString(UpdateArticleDto);
        String actualJSON = mockMvc.perform(put("http://localhost:8080/api/v1/articles/update/" + UpdateArticleDto.articleName()).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArticleDtoUpdated)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(jsonArticleDtoUpdated, actualJSON);
    }


    @Test
    void testDeleteArticle() throws Exception {
        when(microSkillService.addMicroskill(any(MicroSkillDto.class))).thenReturn(microSkillDto1);
        when(articleService.addArticle(any(ArticleDto.class))).thenReturn(ArticleDto1);
        doNothing().when(articleService).deleteArticle(ARTICLE_NAME3);

        mockMvc.perform(delete("http://localhost:8080/api/v1/articles/" + URLEncoder.encode(ARTICLE_NAME3, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllArticle() throws Exception {
        List<ArticleDto> articlesList = Collections.singletonList(ArticleDto1);
        when(articleService.allArticles()).thenReturn(articlesList);

        String actualJSON = mockMvc.perform(get("http://localhost:8080/api/v1/articles/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<ArticleDto> actualArticlesList = mapper.readValue(actualJSON, new TypeReference<>() {
        });
        assertEquals(articlesList, actualArticlesList);
    }
}
