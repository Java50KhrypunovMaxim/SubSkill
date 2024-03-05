package com.subskill.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subskill.config.ObjectMapperConfig;
import com.subskill.dto.ArticleDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.service.ArticleService;
import com.subskill.service.CartService;
import com.subskill.service.MicroSkillService;

@ActiveProfiles("test")
@SpringBootTest(classes = {CartController.class})
@Import(ObjectMapperConfig.class)
@AutoConfigureMockMvc
public class SubSkillCartControllerTest {
	@MockBean
    CartService cartService;
    @MockBean
    ArticleService articleService;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;
    
    private static final long microSkillId = 1;
    private static final long  cartId = 2;

    private static final String ARTICLENAME1 = "About Java 1 part";
    private static final String ARTICLENAME2 = "About C++";
    private static final String ARTICLENAME3 = "About Python";
    @Test
    void testAddMicroSkillToCart() throws Exception {
    	    String jsonMicroSkillId = mapper.writeValueAsString(microSkillId);
    	    String actualJSON = mockMvc.perform(post("http://localhost:8080/api/v1/cart/add")
    	            .contentType(MediaType.APPLICATION_JSON)
    	            .content(jsonMicroSkillId))
    	            .andExpect(status().isOk())
    	            .andReturn().getResponse().getContentAsString();
    	    assertEquals(jsonMicroSkillId, actualJSON);
    }
    
    @Test
    void deleteMicroSkillFromCart() throws Exception {
//        when(articleService.addArticle(any(ArticleDto.class))).thenReturn(ArticleDto1);
        doNothing().when(articleService).deleteArticle(ARTICLENAME3);

        mockMvc.perform(delete("http://localhost:8080/api/v1/articles/" + URLEncoder.encode(ARTICLENAME3, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
