package com.subskill.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.subskill.dto.ReviewDto;
import com.subskill.models.MicroSkill;

import com.subskill.models.Review;

import com.subskill.models.User;
import com.subskill.service.ReviewService;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewController.class)
class SubSkillReviewControllerTest {

	@MockBean
	ReviewService reviewService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	private static final String TEXT1 = "Rambo";

	MicroSkill microSkill = new MicroSkill();

	ReviewDto reviewDto = new ReviewDto(TEXT1, 4.5, new MicroSkill(), new User());

	@Test
	void testAddReview() throws Exception {
		when(reviewService.addReview(reviewDto)).thenReturn(reviewDto);
		String jsonReviewDto = mapper.writeValueAsString(reviewDto);
		String actualJSON = mockMvc.perform(post("http://localhost:8080/api/v1/review").contentType(MediaType.APPLICATION_JSON)
						.content(jsonReviewDto)).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();
		assertEquals(jsonReviewDto, actualJSON);
	}

	@Test
	void testDeleteReview() throws Exception {
		long id = 5;
		doNothing().when(reviewService).deleteReview(id);
		mockMvc.perform(delete("/api/v1/review/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testFindByMicroSkillName() throws Exception {
		List<Review> expectedReview = new ArrayList<>(List.of(Review.of(reviewDto)));
		String jsonExpected = mapper.writeValueAsString(expectedReview);
		when(reviewService.findByMicroSkillName("Java")).thenReturn(expectedReview);
		String actualJSON = mockMvc.perform(get("/api/v1/review/findbymicroskillname/" + "Java").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertEquals(jsonExpected, actualJSON);
	}
}
