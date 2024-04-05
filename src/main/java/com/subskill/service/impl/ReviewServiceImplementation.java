package com.subskill.service.impl;

import com.subskill.dto.ReviewDto;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.ReviewNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.models.Review;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.ReviewRepository;
import com.subskill.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepo;
    private final MicroSkillRepository microSkillRepo;

    @Override
    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = Review.of(reviewDto);
        reviewRepo.save(review);
        log.debug("Review {} has been saved", reviewDto);

        if (review.getMicroSkill() != null) {
            updateMicroSkillAverageRating(review.getMicroSkill());
        }

        return reviewDto;
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepo.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
        reviewRepo.deleteById(review.getId());
        log.debug("Review with id {} has been deleted", review.getId());

    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> findByMicroSkillId(Long microSkillId) {
        MicroSkill microSkill = microSkillRepo.findById(microSkillId)
                .orElseThrow(MicroSkillNotFoundException::new);
        List<Review> reviews = microSkill.getReviews();
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
