package com.subskill.service;

import com.subskill.dto.TechnologyDto;
import com.subskill.enums.Tags;
import com.subskill.models.Technology;

import java.util.List;

public interface TechnologyService {
    List<TechnologyDto> getAllTechnology();

	Technology getByName(String name);

    Technology getByID(long technology_id);

    List<TechnologyDto> getByProfessionName(Tags name);
}
