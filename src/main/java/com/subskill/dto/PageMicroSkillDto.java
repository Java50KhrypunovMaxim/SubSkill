package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;


import static com.subskill.api.ValidationConstants.MISSING_MICROSKILL_VIEWS_MESSAGE;

public record PageMicroSkillDto(@NotEmpty(message = MISSING_MICROSKILL_VIEWS_MESSAGE)
                                String microSkillViews) {
}
