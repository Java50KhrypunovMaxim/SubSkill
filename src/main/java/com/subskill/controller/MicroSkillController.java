package com.subskill.controller;

import com.subskill.dto.MicroSkillDto;
import com.subskill.service.MicroSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api/v1/microskill")
@RequiredArgsConstructor
@Slf4j
public class MicroSkillController {
    MicroSkillService microSkillService;
    @PostMapping("/add")
     MicroSkillDto addMicroSkill(@RequestBody @Valid MicroSkillDto microSkillDto){
        log.debug("add microskill: received microskill data: {}", microSkillDto);
        return microSkillService.addMicroskill(microSkillDto);
    }
    @PutMapping("/update")
    MicroSkillDto  updateMicroSkill(@RequestBody @Valid MicroSkillDto microSkillDto){
        log.debug("update microskill: received new microskill data: {}", microSkillDto);
        return microSkillService.updateMicroskill(microSkillDto);
    }
    @DeleteMapping("/{id}")
    void deleteMicroSkill(@PathVariable Long id) {
        log.debug("delete microskill : microskill with id {}", id);
        microSkillService.deleteMicroSkill(id);
    }
    @GetMapping("/all")
    List<Double> microSkillRanking(){
        return microSkillService.findByRanking();
    }
    @GetMapping("/{id}/views")
    long getMicroSkillViews(@PathVariable long id) {
        return microSkillService.getViewsCount(id);
    }

}
