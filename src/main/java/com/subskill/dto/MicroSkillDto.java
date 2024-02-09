package com.subskill.dto;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

import static com.subskill.api.ValidationConstants.*;

public record MicroSkillDto(@NotEmpty(message = MISSING_MICROSKILL_NAME_MESSAGE)
                            String name,
                            @NotEmpty(message = MISSING_MICROSKILL_DESCRIPTION_MESSAGE)
                            String description,
                            @NotEmpty(message = MISSING_MICROSKILL_PHOTO_MESSAGE)
                            String photo,
                            @NotEmpty(message = MISSING_MICROSKILL_LEARNING_TIME_MESSAGE)
                            String learningTime,
                            @NotEmpty(message = MISSING_MICROSKILL_TAGS_MESSAGE)
                            List<Tags> tags,
                            @NotNull(message = MISSING_MICROSKILL_LEVEL_MESSAGE)
                            Level level,
                            List<ArticleDto> articles,
                            @NotNull(message = TECHNOLOGY_ID_MISSING)
                            @Min(value = 1)
                            Long technologyId
) {

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MicroSkillDto other = (MicroSkillDto) obj;
        return Objects.equals(name, other.name);
    }


}
