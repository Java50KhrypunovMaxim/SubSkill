package com.subskill.controller;


import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.repository.TechnologyRepository;
import com.subskill.service.MicroSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Microskill", description = "Technology card with microSkill")
@RestController
@RequestMapping("api/v1/microskill")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class MicroSkillController {
    private final MicroSkillService microSkillService;
    private final TechnologyRepository technologyRepository;


    @Operation(summary = "Add new MicroSkill card")
    @PostMapping("/add")
    @Parameter(name = "microSkillDto", description = "We use microSkillDto when adding new MicroSkill")
    MicroSkillDto addMicroSkill(@RequestBody @Valid MicroSkillDto microSkillDto) {
        log.debug("add microskill: received microskill data: {}", microSkillDto);
        return microSkillService.addMicroskill(microSkillDto);
    }

    @Operation(summary = "Delete MicroSkill card with this id MicroSkill")
    @DeleteMapping("/{id}")
    void deleteMicroSkill(@PathVariable Long id) {
        log.debug("delete microskill : microskill with id {}", id);
        microSkillService.deleteMicroSkill(id);
    }

    @Operation(summary = "Find ranking of MicroSkill card")
    @GetMapping("/ranking")
    List<Double> microSkillRanking() {
        return microSkillService.findByRanking();
    }

    @Operation(summary = "Find number of views of MicroSkill card")
    @GetMapping("/{id}/views")
    long getMicroSkillViews(@PathVariable long id) {
        return microSkillService.getViewsCount(id);
    }


    @Operation(summary = "Update MicroSkill card")
    @GetMapping("/update")
    ProductMicroSkillDto updateMicroSkill(@RequestBody @Valid ProductMicroSkillDto productMicroSkillDto) {
        log.debug("update microskill: received new microskill data: {}", productMicroSkillDto);
        return microSkillService.updateMicroskill(productMicroSkillDto);
    }

    @Operation(summary = "Get all MicroSkill with pagination and sorting")
    @GetMapping("/all")
    public Page<MicroSkill> getAllMicroSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, sortBy);

        return microSkillService.findMicroSkillByPage(paging);
    }
    @Operation(summary = "Find technology of MicroSkill")
    @GetMapping("/technology")
    private List<Technology> findByProffesionName(@RequestParam String name) {
        log.info("We get technology microskill: ");
        return technologyRepository.findByProfessionName(name);
    }
    @GetMapping("/byRating")
    public Page<MicroSkill> getAllMicroSkillsByRating(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam String rating,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, "rating");

        return microSkillService.findMicroSkillByRatingWithPage(paging, rating);
    }
    @Operation(summary = "Get all MicroSkill by name with pagination and sorting")
    @GetMapping("/byName")
    public Page<MicroSkill> getAllMicroSkillsByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam String name,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, "name");

        return microSkillService.findMicroSkillByNameWithPage(paging, name);
    }
}
