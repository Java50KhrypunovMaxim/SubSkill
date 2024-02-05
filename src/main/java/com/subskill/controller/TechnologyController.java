package com.subskill.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.subskill.models.Technology;
import com.subskill.service.TechnologyService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/v1/technology")
@RequiredArgsConstructor
@Slf4j
public class TechnologyController {

	private final TechnologyService technologyService;

	@GetMapping("/name/{name}")
	Technology getByName(@PathVariable String name) {
		log.debug("Technology: received  {}", name);
		return technologyService.getByName(name);
	}

	@GetMapping("/id/{id}")
	Technology getByID(@PathVariable long id) {
		log.debug("Technology: received  {}", id);
		return technologyService.getByID(id);
	}

	@GetMapping("/all")
	List<String> listOfArticles() {
		log.debug("List of technologies have been received");
		return technologyService.getAllTechnology();
	}
}