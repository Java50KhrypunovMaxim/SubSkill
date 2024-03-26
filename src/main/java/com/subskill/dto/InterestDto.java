package com.subskill.dto;

import com.subskill.enums.Tags;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import static com.subskill.api.ValidationConstants.*;


public record InterestDto(@NotEmpty(message = INTEREST_ID_MISSING)
                          Long id, List<Tags> tags) {

}
