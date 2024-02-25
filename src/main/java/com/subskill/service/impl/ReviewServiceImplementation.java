package com.subskill.service.impl;

import java.util.List;
import java.util.Optional;

import com.subskill.service.ReviewService;
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

    @Override
    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = Review.of(reviewDto);
        reviewRepo.save(review);
        log.debug("Review {} has been saved", reviewDto);
        updateMicroSkillAverageRating(review.getMicroSkill());
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

    @Transactional(readOnly = true)
    @Override
    public List<Review> findByMicroSkillName(String microSkillName) {
        List<Review> technology = reviewRepo.findByMicroSkillName(microSkillName);
        log.debug("All reviews  for {} ", microSkillName);
        return Optional.of(technology)
                .filter(list -> !list.isEmpty())
                .orElseThrow(ReviewNotFoundException::new);
    }

    @Transactional
    public void updateMicroSkillAverageRating(MicroSkill microSkill) {
        double averageRating = microSkill.calculateAverageRating();
        microSkill.setRating(averageRating);
        microSkillRepo.save(microSkill);
    }

}
