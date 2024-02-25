package com.subskill.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;
import com.subskill.service.SavedMicroskillService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/savemicroSkill")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class SaveMicroSkillController {
	
	SavedMicroskillService savedMicroskillService;

    @Operation(summary = "Save MicroSkills to User")
    @PostMapping("/add/microskill{microskillId}")
    SaveMicroskill addMicroSkillToUser(@PathVariable long microskillId) {
        log.debug("Add MicroSkill ID: {} to User ID: {}", microskillId);
        return savedMicroskillService.addMicroSkillToUser(microskillId);
    }
    
    @Operation(summary = "Remove MicroSkills from User")
    @DeleteMapping("/delete/microskill{microskillId}")
    void deleteMicroSkillFromCart(@PathVariable long microskillId) {
        log.debug("remove microSkill: remove cart microskill {} from user ID {}", microskillId);
        savedMicroskillService.deleteMicroSkillFromUser(microskillId);
    }
    
    @Operation(summary = "List of MicroSkills of User")
    @GetMapping("/all")
    Set<MicroSkill> allMicroSkillsOfUser(@PathVariable long userId) {
        log.debug("List of microslills of user id{}", userId);
        return savedMicroskillService.allMicroSkillsOfUser(userId);
    }
}


