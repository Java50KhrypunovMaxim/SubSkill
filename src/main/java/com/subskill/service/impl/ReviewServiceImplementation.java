package com.subskill.service.impl;

import java.util.List;

import com.subskill.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.subskill.dto.ReviewDto;
import com.subskill.exception.ReviewNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.models.Review;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepo;
    private final MicroSkillRepository microSkillRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        if (review.getRating() != null) {
            reviewRepo.save(review);
            log.debug("Review {} has been saved", reviewDto);
            updateMicroSkillAverageRating(review.getMicroSkill());
        }

        return reviewDto;
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepo.findByid(reviewId).orElseThrow(ReviewNotFoundException::new);
        reviewRepo.deleteById(review.getId());
        log.debug("Review with id {} has been deleted", review.getId());
        updateMicroSkillAverageRating(review.getMicroSkill());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> findByMicroSkillName(String microSkillName) {
        List<Review> reviews = reviewRepo.findByMicroSkillName(microSkillName);
        log.debug("All reviews for micro skill: {}", microSkillName);
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException();
        }
        return reviews.stream()
                .map(Review::build)
                .toList();
    }

    @Transactional
    public void updateMicroSkillAverageRating(MicroSkill microSkill) {
        double averageRating = microSkill.calculateAverageRating();
        microSkill.setRating(averageRating);
        microSkillRepo.save(microSkill);
    }
}
