package com.subskill.service;

import java.util.List;

import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.ReviewDto;
import com.subskill.models.Review;


public interface ReviewService {
	ReviewDto addReview (ReviewDto reviewDto);
	void deleteReview(Long review_id);
    List<ReviewDto> findByMicroSkillId(Long microskillId);
}
