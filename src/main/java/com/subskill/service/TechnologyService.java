package com.subskill.service;

import java.util.List;

import com.subskill.dto.TechnologyDto;
import com.subskill.models.Technology;

public interface TechnologyService {
    List<TechnologyDto> getAllTechnology();

	Technology getByName(String name);

    Technology getByID(long technology_id);

    List<TechnologyDto> getByProfessionName(String name);
}
