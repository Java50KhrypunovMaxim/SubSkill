package com.subskill.service;

import java.util.List;

import com.subskill.models.Technology;

public interface TechnologyService {
	List<String> getAllTechology();
	Technology getByName(String name);
	Technology getByID(long ID);
}
