package com.subskill.controller;

import com.subskill.dto.TechnologyDto;
import com.subskill.models.Technology;
import com.subskill.service.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/technology")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class TechnologyController {

    private final TechnologyService technologyService;

    @Operation(summary = "Name of technology for MicroSkill by name")
    @GetMapping("/name/{name}")
    Technology getByName(@PathVariable String name) {
        log.debug("Technology: received  {}", name);
        return technologyService.getByName(name);
    }

    @Operation(summary = "Name of technology for MicroSkill by id")
    @GetMapping("/id/{id}")
    Technology getByID(@PathVariable long id) {
        log.debug("Technology: received  {}", id);
        return technologyService.getByID(id);
    }

    @Operation(summary = "List of technologys for MicroSkill")
    @GetMapping("/all")
    List<TechnologyDto> listOfTechnology() {
        log.debug("List of technologies have been received");
        return technologyService.getAllTechnology();
    }

    @GetMapping("/profession/{name}")
    List<TechnologyDto> getByProfessionName(@PathVariable String name) {
        log.debug("List of technologies by profession name {} have been received", name);
        return technologyService.getByProfessionName(name);
    }
}