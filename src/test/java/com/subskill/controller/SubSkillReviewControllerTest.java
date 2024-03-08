package com.subskill.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import com.subskill.config.ObjectMapperConfig;
import com.subskill.dto.AuthDto.LoginDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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

import com.subskill.dto.ReviewDto;
import com.subskill.models.MicroSkill;

import com.subskill.models.Review;

import com.subskill.models.User;
import com.subskill.service.ReviewService;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Import(ObjectMapperConfig.class)
@SpringBootTest(classes = {ReviewController.class})
class SubSkillReviewControllerTest {

    @MockBean
    ReviewService reviewService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private static final String TEXT1 = "Rambo";
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

//    @Test
//    void testAddReview() throws Exception {
//        when(reviewService.addReview(reviewDto)).thenReturn(reviewDto);
//        String jsonReviewDto = mapper.writeValueAsString(reviewDto);
//
//        String actualJSON = mockMvc.perform(post("/api/v1/review")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                        .content(jsonReviewDto))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        assertEquals(jsonReviewDto, actualJSON);
//    }

    @Test
    void testDeleteReview() throws Exception {
        long id = 5;
        doNothing().when(reviewService).deleteReview(id);
        mockMvc.perform(delete("/api/v1/review/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken))
                .andExpect(status().isOk());
    }

//    @Test
//    void testFindByMicroSkillName() throws Exception {
//        List<Review> expectedReview = new ArrayList<>(List.of(Review.of(reviewDto)));
//        String jsonExpected = mapper.writeValueAsString(expectedReview);
//        when(reviewService.findByMicroSkillName("Java")).thenReturn(expectedReview);
//        String actualJSON = mockMvc.perform(get("/api/v1/review/find-by-name/" + "Java")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        assertEquals(jsonExpected, actualJSON);
//    }
}
