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
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subskill.config.ObjectMapperConfig;
import com.subskill.dto.ArticleDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.AuthDto.LoginDto;
import com.subskill.service.ArticleService;
import com.subskill.service.CartService;
import com.subskill.service.MicroSkillService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@ActiveProfiles("test")
@SpringBootTest(classes = {CartController.class})
@Import(ObjectMapperConfig.class)
@AutoConfigureMockMvc
public class SubSkillCartControllerTest {
    @MockBean
    CartService cartService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private static final long microSkillId = 10;
    private static final long userId = 10;
    private static String authToken;

    @BeforeAll
    public static void setup() {
        LoginDto loginDto = new LoginDto("12345", "user@example.com");
        authToken = Jwts.builder()
                .setSubject(loginDto.email())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60L * 1000))
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
    }

    @Test
    void testAddMicroSkillToCart() throws Exception {
        String jsonMicroSkillId = mapper.writeValueAsString(microSkillId);
        String actualJSON = mockMvc.perform(post("http://localhost:8080/api/v1/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                        .content(jsonMicroSkillId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(jsonMicroSkillId, actualJSON);
    }

    @Test
    void deleteMicroSkillFromCart() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/v1/cart/delete/" + microSkillId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void allMicroSkillsInCart() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/v1/cart/all/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken))
                .andExpect(status().isOk());
    }
}
