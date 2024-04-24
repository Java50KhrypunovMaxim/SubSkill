package com.subskill.service;

import com.subskill.dto.ReviewDto;
import com.subskill.dto.UserDto;
import com.subskill.models.Review;
import com.subskill.models.User;
import com.subskill.repository.ReviewRepository;
import com.subskill.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:data_for_the_database.sql"})
public class SubSkillReviewServiceTest {
    @Autowired
    ReviewRepository reviewRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewService reviewService;
    
    private static final String REVIEW_SERVICE_TEST = "Review Service Test: ";
    
    @Test
    @DisplayName(REVIEW_SERVICE_TEST + "Add Review")
    void testAddReview() {
    	long userID = 10;
		User userFromRepo = userRepository.getById(userID);
    	UserDto userDto = userFromRepo.build();
    	ReviewDto newReview = new ReviewDto(25L, "So-so course", 4.5, 11L, userDto);
    	reviewService.addReview(newReview);
    	Review mustBe = reviewRepository.getById(25L);
    	assertEquals(mustBe.build(), newReview);
    }
    
    @Test
    @DisplayName(REVIEW_SERVICE_TEST + "Delete Review")
    void testDeleteReview() {
    	reviewService.deleteReview(13L);
    	List<ReviewDto> realList = reviewService.findByMicroSkillId(13L);
    	assertEquals(0, realList.size());
    }
    
    
    @Test
    @DisplayName(REVIEW_SERVICE_TEST + "Find All Reviews By Microskill")
    void testFindAllReviewsByMicroskill() {
    	List<ReviewDto> realList = reviewService.findByMicroSkillId(12L);
    	assertEquals(1, realList.size());
    }
    
    
}
