package com.subskill.controller;

import static com.subskill.api.ValidationConstants.*;

import java.util.List;

import com.subskill.dto.MicroSkillDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.subskill.dto.ReviewDto;
import com.subskill.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Validated
@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Register a new review")
    @PostMapping()
    ReviewDto addReview(@RequestBody  ReviewDto reviewDto) {
        log.debug("registerReview: received review data: {}", reviewDto);
        return reviewService.addReview(reviewDto);
    }

    @Operation(summary = "Delete our review based on id")
    @DeleteMapping("delete/{id}")
    void deleteReview(@NotNull(message = MISSING_ID_OF_REVIEW) @PathVariable Long id) {
        log.debug("delete review: review with id {}", id);
        reviewService.deleteReview(id);
    }

    @Operation(summary = "Find list of reviews based on microSkillId")
    @GetMapping("/find-by-id/{microskillId}")
    List<ReviewDto> findByMicroSkillName(@PathVariable Long microskillId) {
        log.debug("List of Review: received review by microSkillId {}", microskillId);
        return reviewService.findByMicroSkillId(microskillId);
    }
}
