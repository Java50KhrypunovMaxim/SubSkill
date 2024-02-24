package com.subskill.controller;

import java.util.List;
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
    
    @Operation(summary = "Add new MicroSkills to User")
    @PostMapping("/add/user{user_id}microskill{microskill_id}")
    SaveMicroskill addMicroSkillToUser(@PathVariable long user_id, long microskill_id) {
        log.debug("Add MicroSkill ID: {} to User ID: {}", microskill_id, user_id);
        return savedMicroskillService.addMicroSkillToUser(user_id, microskill_id);
    }
    
    @Operation(summary = "Remove MicroSkills from User")
    @DeleteMapping("/delete/user{user_id}microskill{microskill_id")
    void deleteMicroSkillFromCart(@PathVariable long user_id, long microskill_id) {
        log.debug("remove microSkill: remove cart microskill {} from user ID {}", microskill_id, user_id );
        savedMicroskillService.deleteMicroSkillFromUser(user_id, microskill_id);
    }
    
    @Operation(summary = "List of MicroSkills of User")
    @GetMapping("/all")
    List<MicroSkill> allMicroSkillsOfUser(@PathVariable long user_id) {
        log.debug("List of microslills of user id{}", user_id); 
        return savedMicroskillService.allMicroSkillsOfUser(user_id);
    }
}


