package com.subskill.dto;



import com.subskill.models.Technology;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.util.Objects;

import static com.subskill.api.ValidationConstants.*;

public record MicroSkillDto(@NotEmpty(message = MISSING_MICROSKILL_NAME_MESSAGE)
                            String microSkillname,
                            @NotNull(message = MISSING_MICROSKILL_RATING_MESSAGE)
                            Double microSkillrating,
                            @NotEmpty(message = MISSING_MICROSKILLS_PHOTO_MESSAGE)
                            String microSkillphoto,
                            @NotEmpty(message = TECHNOLOGY_ID_MISSING)
                            Technology technologyId
) {

    @Override
    public int hashCode() {
        return Objects.hash(microSkillname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MicroSkillDto  other = (MicroSkillDto) obj;
        return Objects.equals(microSkillname, other.microSkillname);
    }


}
