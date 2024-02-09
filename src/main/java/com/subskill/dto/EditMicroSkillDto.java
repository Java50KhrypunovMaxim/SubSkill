package com.subskill.dto;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import static com.subskill.api.ValidationConstants.*;

public record EditMicroSkillDto(@NotEmpty(message = MISSING_MICROSKILL_NAME_MESSAGE)
                                String name,
                                String description,
                                String photo,
                                String learningTime,
                                List<Tags> tags,
                                Level level,
                                List<ArticleDto> articles,
                                Long technologyId) {
}
