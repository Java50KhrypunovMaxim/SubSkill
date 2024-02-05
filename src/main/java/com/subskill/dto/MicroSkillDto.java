package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

import static com.subskill.api.ValidationConstants.*;

public record MicroSkillDto(@NotEmpty(message = MISSING_MICROSKILL_NAME_MESSAGE)
                            String microSkillName,
                            @NotNull(message = MISSING_MICROSKILL_RATING_MESSAGE)
                            Double microSkillRating,
                            @NotEmpty(message = MISSING_MICROSKILLS_PHOTO_MESSAGE)
                            String microSkillPhoto,
                            @NotEmpty
                            List<ArticleDto> articles
                            //         @NotEmpty(message = TECHNOLOGY_ID_MISSING)
                            //     Technology technologyId заглушка
) {

    @Override
    public int hashCode() {
        return Objects.hash(microSkillName);
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
        return Objects.equals(microSkillName, other.microSkillName);
    }


}
