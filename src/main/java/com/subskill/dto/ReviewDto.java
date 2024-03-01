package com.subskill.dto;


import static com.subskill.api.ValidationConstants.*;
import com.subskill.models.MicroSkill;
import com.subskill.models.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReviewDto(
		@NotEmpty(message = MISSING_ID_OF_REVIEW)
		Long id,
		
		@NotEmpty (message=MISSING_TEXT_REVIEW_MESSAGE)
		String text,
		
		@NotNull(message = MISSING_RATING_MESSAGE)
		@Min(value = 1, message = MIN_RARING)
		@Max(value = 5, message = MAX_RARING)
		Double rating,
		
		@NotNull(message = MISSING_MICRO_SKILLS_MESSAGE)
		MicroSkill microSkill,
		
		@NotNull(message = MISSING_USER_MESSAGE)
		User user
		
	) {

}
