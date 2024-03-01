package com.subskill.service;

import java.util.List;

import com.subskill.models.Technology;

public interface TechnologyService {
    List<Technology> getAllTechnology();

	Technology getByName(String name);


	Technology getByID(long technology_id);
	
	List <Technology> getByProfessionName(String name);
}
