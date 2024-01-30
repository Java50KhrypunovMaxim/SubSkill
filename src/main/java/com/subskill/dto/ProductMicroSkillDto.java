package com.subskill.dto;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import static com.subskill.api.ValidationConstants.*;


public record ProductMicroSkillDto(@NotEmpty(message = MISSING_MICROSKILL_NAME_MESSAGE)
                                   String microSkillName,
                                @NotNull(message = MISSING_MICROSKILL_RATING_MESSAGE)
                                   Double microSkillRating,
                                @NotEmpty(message = MISSING_MICROSKILLS_PHOTO_MESSAGE)
                                   String microSkillPhoto,
                                @NotEmpty(message = MISSING_MICROSKILL_DESCRIPTION_MESSAGE)
                                String microSkillDescription,
                                 @NotEmpty(message = MISSING_MICROSKILL_LEVEL_MESSAGE)
                                Level microSkillLevel,
                                 @NotEmpty(message = MISSING_MICROSKILL_TAGS_MESSAGE)
                                Tags microSkillTags,
                                @NotEmpty(message = MISSING_MICROSKILL_LEARNINGTIME_MESSAGE)
                                   String microSkillLearningTime) {
}
