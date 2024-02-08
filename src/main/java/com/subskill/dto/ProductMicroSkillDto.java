package com.subskill.dto;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import static com.subskill.api.ValidationConstants.*;


public record ProductMicroSkillDto(@NotEmpty(message = MISSING_MICROSKILL_NAME_MESSAGE)
                                   @NotNull(message = MISSING_MICROSKILL_NAME_MESSAGE)
                                   String name,
                                @NotNull(message = MISSING_MICROSKILL_RATING_MESSAGE)
                                   Double rating,
                                @NotEmpty(message = MISSING_MICROSKILL_PHOTO_MESSAGE)
                                   String photo,
                                @NotEmpty(message = MISSING_MICROSKILL_DESCRIPTION_MESSAGE)
                                   String description,
                                 @NotEmpty(message = MISSING_MICROSKILL_LEVEL_MESSAGE)
                                   Level level,
                                 @NotEmpty(message = MISSING_MICROSKILL_TAGS_MESSAGE)
                                   Tags tags,
                                @NotEmpty(message = MISSING_MICROSKILL_LEARNING_TIME_MESSAGE)
                                   String learningTime) {
}
