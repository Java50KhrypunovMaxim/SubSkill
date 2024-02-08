package com.subskill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.subskill.dto.ReviewDto;
import com.subskill.exception.ReviewNotFoundException;
import com.subskill.models.Review;
import com.subskill.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImplementation implements ReviewService{

	private final ReviewRepository reviewRepo;
	
	@Override
	public ReviewDto addReview(ReviewDto reviewDto) {
		Review review = Review.of(reviewDto);
		reviewRepo.save(review);
		log.debug("Review {} has been saved", reviewDto);
		return reviewDto;
	}

	@Override
	public void deleteReview(Long id) {
		Review review = reviewRepo.findByid(id).orElseThrow(ReviewNotFoundException::new);
	    reviewRepo.deleteById(review.getId());
	    log.debug("Review with id {} has been deleted", review.getId());
	}

	@Override
	public List <Review> findByMicroSkillName(String microSkillName) {
		   List<Review> technology = reviewRepo.findByMicroSkillName(microSkillName);
	        log.debug("All reviews  for {} ", microSkillName);
	        return Optional.of(technology)
	                .filter(list -> !list.isEmpty())
	                .orElseThrow(ReviewNotFoundException::new);
	}

}