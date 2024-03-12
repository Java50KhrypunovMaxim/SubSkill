package com.subskill.controller;


import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.service.MicroSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/microskill")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin(maxAge = 3600, origins = "*")
public class MicroSkillController {

    private final MicroSkillService microSkillService;

    @Operation(summary = "Add new MicroSkill card")
    @PostMapping("/add")
    @Parameter(name = "microSkillDto", description = "We use microSkillDto when adding new MicroSkill")
     public MicroSkillDto addMicroSkill(@RequestBody MicroSkillDto microSkillDto) {
        log.debug("add microskill: received microskill data: {}", microSkillDto);
        return microSkillService.addMicroSkill(microSkillDto);
    }

    @Operation(summary = "Delete MicroSkill card with this id MicroSkill")
    @DeleteMapping("/{id}")
    void deleteMicroSkill(@PathVariable Long id) {
        log.debug("delete microskill : microskill with id {}", id);
        microSkillService.deleteMicroSkill(id);
    }

    @Operation(summary = "Find number of views of MicroSkill card")
    @GetMapping("/{id}/views")
    long getMicroSkillViews(@PathVariable long id) {
        return microSkillService.getViewsCount(id);
    }

    @Operation(summary = "Find new MicroSkills card")
    @GetMapping("/new")
    Page<MicroSkillDto> getNewMicroSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "creationDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, sortBy);

        return microSkillService.freshMicroSkills(paging);
    }
    @Operation(summary = "Find new MicroSkills card")
    @GetMapping("/mostViews")
    Page<MicroSkillDto> getRevisedMicroSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "views") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, sortBy);

        return microSkillService.mostVisitedMicroSkills(paging);
    }
    @Operation(summary = "Get most popular MicroSkills")
    @GetMapping("/mostPopular")
    public Page<MicroSkillDto> mostPopularMicroSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "popularity") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable paging = PageRequest.of(page, size, sortDirection, sortBy);

        return microSkillService.findMostPopularMicroSkills(paging);
    }
    @Operation(summary = "Update MicroSkill card")
    @PutMapping("/update/{id}")
    void updateMicroSkill(@PathVariable Long id,@RequestBody EditMicroSkillDto editMicroSkillDto) {
        log.debug("update microskill: received new microskill data: {}", editMicroSkillDto);
        microSkillService.updateMicroSkill(editMicroSkillDto,id);
    }

    @Operation(summary = "Get all MicroSkill with pagination and sorting")
    @GetMapping("/all-paging")
    public Page<MicroSkillDto> getAllMicroSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, sortBy);

        return microSkillService.findMicroSkillByPage(paging);
    }

    @GetMapping("/all")
    public List<MicroSkillDto> findAll() {
        return microSkillService.findAllMicroSkills();
    }

    @Operation(summary = "Find technology of MicroSkill")
    @GetMapping("/technology")
    Page<MicroSkillDto> findTechnologyOfMicroSkill(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam String name,
                                                   @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, "name");
        log.info("We get technology microskill: ");
        return microSkillService.findTechnology(name, paging);
    }

    @GetMapping("/find-by-rating")
    public Page<MicroSkillDto> getAllMicroSkillsByRating(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam Double rating,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, "rating");

        return microSkillService.findMicroSkillByRatingWithPage(paging, rating);
    }

    @Operation(summary = "Get all MicroSkill by name with pagination and sorting")
    @GetMapping("/find-by-name")
    public Page<MicroSkillDto> getAllMicroSkillsByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam String name,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, "name");

        return microSkillService.findMicroSkillByNameWithPage(paging, name);
    }

    @Operation(summary = "Get popularity of  MicroSkill card by views and rating")
    @GetMapping("{id}/popularity")
    public MicroSkillDto getByPopularity(@PathVariable long id) {
        return microSkillService.findMicroSkillPopularity(id);
    }

    @Operation(summary = "Get MicroSkill card by id")
    @GetMapping("find-by-id/{id}")
    public MicroSkillDto findMicroSkillById(@PathVariable long id) {
        return microSkillService.findMicroSkillById(id);
    }



    @Operation(summary = "Update price of MicroSkill")
    @PutMapping("/change-price")
    void updatePriceMicroSkill(@RequestParam long microSkill_id, @RequestParam Double price) {
        log.debug("update price of  microskill within{}: received new microskill price: {}", microSkill_id, price);
        microSkillService.updatePriceMicroSkill(microSkill_id, price);
    }

    @Operation(summary = "Find MicroSkill by level")
    @GetMapping("/find-by-level")
    public Page<MicroSkillDto> findMicroSkillByLevel(@RequestParam Level level,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "4") int size,
                                                     @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, "level");
        log.debug("finding level {} of MicroSkill", level);
        return microSkillService.findLevelFromMicroSkill(level, paging);
    }

    @Operation(summary = "Find MicroSkill by tag")
    @GetMapping("/find-by-tags")
    public Page<MicroSkillDto> findMicroSkillByTag(@RequestParam Tags tags,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "4") int size,
                                                   @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, "tags");
        log.debug("finding tags {} of MicroSkill", tags);
        return microSkillService.findMicroSkillByTag(tags, paging);
    }

    @Operation(summary = "Get top MicroSkill deals")
    @GetMapping("/get-today-deals")
    public Page<MicroSkillDto> getTodayBestDeals(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "4") int size) {

        Pageable paging = PageRequest.of(page, size, Sort.Direction.ASC, "tags");
        return microSkillService.getBestDealsByToday(paging);
    }
}
