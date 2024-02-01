package com.subskill.controller;


import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import com.subskill.models.MicroSkill;
import com.subskill.service.MicroSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Microskill", description = "Technology card with microSkill")
@RestController
@RequestMapping("api/v1/microskill")
@RequiredArgsConstructor
@Slf4j
public class MicroSkillController {
    private final MicroSkillService microSkillService;

    @Operation(
            summary = "Controller for adding Microskilll",
            description = "We add new MicroSkill card",
            tags = { "add", "get" })
    @PostMapping("/add")
    @Parameter(name = "microSkillDto", description = "We use microSkillDto when adding new MicroSkill")
     MicroSkillDto addMicroSkill(@RequestBody @Valid MicroSkillDto microSkillDto){
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
    List<Double> microSkillRanking(){
        return microSkillService.findByRanking();
    }

    @Tag(name = "views", description = "We find number of views of MicroSkill card")
    @GetMapping("/{id}/views")
    long getMicroSkillViews(@PathVariable long id) {
        return microSkillService.getViewsCount(id);
    }

    @Tag(name = "update", description = "We update MicroSkill card")
    @GetMapping("/update")
    ProductMicroSkillDto updateMicroSkill(@RequestBody @Valid ProductMicroSkillDto productMicroSkillDto) {
        log.debug("update microskill: received new microskill data: {}", productMicroSkillDto);
        return microSkillService.updateMicroskill(productMicroSkillDto);
    }
    @Tag(name = "All", description = "We get all of MicroSkill ")
    @GetMapping("/all")
    List<MicroSkill> getItAll(){
        return microSkillService.findAllMicroSkill();
    }

}
