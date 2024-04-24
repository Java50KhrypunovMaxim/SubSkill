package com.subskill.service;

import com.subskill.dto.ReviewDto;

import java.util.List;


public interface ReviewService {
	ReviewDto addReview (ReviewDto reviewDto);
	void deleteReview(Long review_id);
    List<ReviewDto> findByMicroSkillId(Long microskillId);
}
