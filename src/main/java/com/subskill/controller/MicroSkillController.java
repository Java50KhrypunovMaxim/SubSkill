package com.subskill.controller;


import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.repository.TechnologyRepository;
import com.subskill.service.MicroSkillService;
import com.subskill.service.TechnologyService;
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
    MicroSkillDto addMicroSkill(@RequestBody  MicroSkillDto microSkillDto) {
        log.debug("add microskill: received microskill data: {}", microSkillDto);
        return microSkillService.addMicroskill(microSkillDto);
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


    @Operation(summary = "Update MicroSkill card")
    @PutMapping("/update")
    void updateMicroSkill(@RequestBody  EditMicroSkillDto microSkillDto) {
        log.debug("update microskill: received new microskill data: {}", microSkillDto);
        microSkillService.updateMicroSkill(microSkillDto);
    }

    @Operation(summary = "Get all MicroSkill with pagination and sorting")
    @GetMapping("/findSixPages")
    public Page<MicroSkill> getAllMicroSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, sortDirection, sortBy);

        return microSkillService.findMicroSkillByPage(paging);
    }
//    @GetMapping("/all")
//    public List<MicroSkill>  findAll(){
//        return microSkillService.findAllMicroSkills();
//    }
    @Operation(summary = "Find technology of MicroSkill")
    @GetMapping("/technology")
    List<MicroSkill> findTechnologyOfMicroSkill(@RequestParam String name) {
        log.info("We get technology microskill: ");
        return microSkillService.findTechnology(name);
    }

    @GetMapping("/byRating")
    public Page<MicroSkill> getAllMicroSkillsByRating(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam Double rating,
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

    @Operation(summary = "Get popularity of  MicroSkill card by views and rating")
    @GetMapping("{id}/popularity")
    public MicroSkill getByPopularity(@PathVariable long id) {
        return microSkillService.findMicroSkillPopularity(id);
    }

    @Operation(summary = "Get MicroSkill card by id")
    @GetMapping("{id}/find_microskill")
    public MicroSkill findMicroSkill(@PathVariable long id) {
        return microSkillService.findMicroSkill(id);
    }


    @Operation(summary = "Update price of MicroSkill")
    @PutMapping("/changePrice")
    void updatePriceMicroSkill(@RequestParam long microSkill_id , @RequestParam Double price) {
        log.debug("update price of  microskill within{}: received new microskill price: {}", microSkill_id , price);
        microSkillService.updatePriceMicroSkill(microSkill_id , price);
    }

    @Operation(summary = "Find MicroSkill by level")
    @GetMapping("/find-by-level")
    public List<MicroSkillDto> findMicroSkillByLevel(@RequestParam Level level) {
        log.debug("finding level {} of MicroSkill", level);
        return microSkillService.findLevelFromMicroSkill(level);
    }

    @Operation(summary = "Find MicroSkill by tag")
    @GetMapping("/find-by-tags")
    public List<MicroSkillDto> findMicroSkillByTag(@RequestParam Tags tags) {
        log.debug("finding tags {} of MicroSkill", tags);
        return microSkillService.findMicroSkillByTag(tags);
    }
    @Operation(summary = "Get top MicroSkill deals")
    @GetMapping("/get-today-deals")
    public List<MicroSkillDto> getTodayBestDeals() {
        return microSkillService.getBestDealsByToday();
    }
    
    @Operation(summary = "Sort of MicroSkill by popularity")
    @GetMapping("/sortByPopularityMicroSkill")
    private List<MicroSkillDto> sortByPopularityMicroSkill() {
        log.info("We have sorted microSkills by popularity ");
        return microSkillService.sortByPopularityMicroSkill();
    }
}
