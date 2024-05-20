package com.subskill.controller;


import com.subskill.dto.MicroSkillDto;
import com.subskill.service.MySavedMicroSkillsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/mySavedMicroSkill")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class MySavedMicroSkillController {

    private final MySavedMicroSkillsService mySavedMicroSkillsService;

    @PostMapping("/add/{microskillId}")
    public ResponseEntity<Void> addMicroSkill(@PathVariable long microskillId ) {
        mySavedMicroSkillsService.addMicroSkill(microskillId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<Set<MicroSkillDto>> findAll() {
        Set<MicroSkillDto> saveMicroskills = mySavedMicroSkillsService.allSavedMicroSkillsOfUser();
        return ResponseEntity.ok(saveMicroskills);
    }
}