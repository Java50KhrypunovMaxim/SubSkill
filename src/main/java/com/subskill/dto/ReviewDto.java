package com.subskill.dto;


import static com.subskill.api.ValidationConstants.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReviewDto(
//		@NotEmpty(message = MISSING_ID_OF_REVIEW)
		Long id,
		
		@NotEmpty (message=MISSING_TEXT_REVIEW_MESSAGE)
		String text,
		
//		@NotNull(message = MISSING_RATING_MESSAGE)
//        @Min(value = 1, message = MIN_RATING)
//        @Max(value = 5, message = MAX_RATING)
		Double rating,
		
//		@NotNull(message = MISSING_MICRO_SKILLS_MESSAGE)
		Long microSkillId,
		
//		@NotNull(message = MISSING_USER_MESSAGE)
        UserDto userDto
		
	) {

}
