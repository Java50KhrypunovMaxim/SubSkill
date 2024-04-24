package com.subskill.dto;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.subskill.api.ValidationConstants.MISSING_ID_OF_SKILLS;

public record EditMicroSkillDto(@NotNull(message = MISSING_ID_OF_SKILLS)
                                Long id,
                                String name,
                                String description,
                                String photo,
                                String learningTime,
                                LocalDateTime lastUpdateTime,
                                String aboutSkill,
                                Integer lessonCount,
                                BigDecimal price,
                                List<Tags> tags,
                                Level level,
                                List<ArticleDto> articles,
                                Long technologyId) {
}
