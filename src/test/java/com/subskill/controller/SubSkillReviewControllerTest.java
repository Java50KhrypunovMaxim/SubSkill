package com.subskill.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subskill.config.ObjectMapperConfig;
import com.subskill.dto.AuthDto.LoginDto;
import com.subskill.dto.ReviewDto;
import com.subskill.dto.UserDto;
import com.subskill.enums.Level;
import com.subskill.enums.Roles;
import com.subskill.enums.Status;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import com.subskill.models.Review;
import com.subskill.models.User;
import com.subskill.service.ReviewService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private static final UserDto USER_DTO = new UserDto("test_user", "user@example.com", "password123", Status.ONLINE, "user.jpg", Roles.USER);

    private static final ReviewDto REVIEW_DTO = new ReviewDto(1L, "Great course", 4.5, 10L, USER_DTO);

    private static String authToken;
    private static final MicroSkill MICROSKILL = MicroSkill.builder()
            .name("Java Programming")
            .photo("java.jpg")
            .creationDate(LocalDate.now())
            .description("Learn Java Programming from scratch")
            .learningTime("3 months")
            .tags(List.of(Tags.BUSINESS))
            .level(Level.ADVANCED)
            .rating(4.5)
            .popularity(100.0)
            .views(500)
            .price(49.99)
            .lessonCount(50)
            .aboutSkill("Java is a powerful and versatile programming language")
            .lastUpdateTime(LocalDateTime.now())
            .build();
    ;

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
    void testAddReview() throws Exception {

        when(reviewService.addReview(REVIEW_DTO)).thenReturn(REVIEW_DTO);
        String jsonReviewDto = mapper.writeValueAsString(REVIEW_DTO);

        String actualJSON = mockMvc.perform(post("/api/v1/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                        .content(jsonReviewDto))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(jsonReviewDto, actualJSON);
    }

    @Test
    void testDeleteReview() throws Exception {
        long id = 5;
        doNothing().when(reviewService).deleteReview(id);
        mockMvc.perform(delete("/api/v1/review/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByMicroSkillName() throws Exception {


        Review review = new Review(
                REVIEW_DTO.id(),
                REVIEW_DTO.text(),
                REVIEW_DTO.rating(),
                MICROSKILL,
                User.of(REVIEW_DTO.userDto())
        );
        List<Review> expectedReview = List.of(review);
        when(reviewService.findByMicroSkillId(1L)).thenReturn(List.of(REVIEW_DTO));

        mockMvc.perform(get("/api/v1/review/find-by-name/Java")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedReview)));
    }
}
