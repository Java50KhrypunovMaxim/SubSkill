package com.subskill.dto;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import static com.subskill.api.ValidationConstants.*;

public record EditMicroSkillDto(@NotNull(message = MISSING_ID_OF_SKILLS)
                                Long id,
                                String name,
                                String description,
                                String photo,
                                String learningTime,
                                LocalDateTime lastUpdateTime,
                                String aboutSkill,
                                Integer lessonCount,
                                Double price,
                                List<Tags> tags,
                                Level level,
                                List<ArticleDto> articles,
                                Long technologyId) {
}
