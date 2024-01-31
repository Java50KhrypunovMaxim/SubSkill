package com.subskill.controller;


import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.PageMicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import com.subskill.service.MicroSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Microskill", description = "Technology card with microSkill")
@RestController
@RequestMapping("api/v1/microskill")
@RequiredArgsConstructor
@Slf4j
public class MicroSkillController {
    MicroSkillService microSkillService;

    @Operation(
            summary = "Controller for adding Microskilll",
            description = "We add new MicroSkill card",
            tags = {"add", "get"})
    @PostMapping("/add")
    @Parameter(name = "microSkillDto", description = "We use microSkillDto when adding new MicroSkill")
    MicroSkillDto addMicroSkill(@RequestBody @Valid MicroSkillDto microSkillDto) {
        log.debug("add microskill: received microskill data: {}", microSkillDto);
        return microSkillService.addMicroskill(microSkillDto);
    }

    @Tag(name = "delete", description = "We delete MicroSkill card with this id MicroSkill")
    @DeleteMapping("/{id}")
    void deleteMicroSkill(@PathVariable Long id) {
        log.debug("delete microskill : microskill with id {}", id);
        microSkillService.deleteMicroSkill(id);
    }

    @Tag(name = "ranking", description = "We find ranking of MicroSkill card")
    @GetMapping("/ranking")
    List<Double> microSkillRanking() {
        return microSkillService.findByRanking();
    }

    @Tag(name = "views", description = "We find number of views of MicroSkill card")
    @GetMapping("/id/{views}")
    ProductMicroSkillDto getMicroSkillViews(@PathVariable long views) {
        log.debug("Get  microskill views: received new microskill data of views: {}", views);
        return microSkillService.getViewsCount(views);
    }

    @Tag(name = "update", description = "We update MicroSkill card")
    @PutMapping("/update")
    ProductMicroSkillDto updateMicroSkill(@RequestBody @Valid ProductMicroSkillDto productMicroSkillDto) {
        log.debug("update microskill: received new microskill data: {}", productMicroSkillDto);
        return microSkillService.updateMicroskill(productMicroSkillDto);
    }

    @Tag(name = "All", description = "We get all of MicroSkill ")
    @GetMapping("/all")
    List<ProductMicroSkillDto> getItAll() {
        log.info("We get all microskill: received all microskill data: ");
        return microSkillService.findAllMicroSkill();
    }

    @GetMapping("/{page}")
    PageMicroSkillDto findAllPageOfMicroSkill(@PathVariable int page,
                                              @RequestParam(required = false, defaultValue = "6") int size) {
        return microSkillService.findAllPage(PageRequest.of(page, size));
    }
}
