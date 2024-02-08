package com.subskill.service;

import java.util.List;

import com.subskill.dto.ReviewDto;
import com.subskill.models.Review;


public interface ReviewService {
	ReviewDto addReview (ReviewDto reviewDto);
	void deleteReview(Long id);
	List <Review> findByMicroSkillName(String MicroSkillName);
}
