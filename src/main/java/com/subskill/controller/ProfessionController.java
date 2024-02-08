package com.subskill.controller;

import com.subskill.dto.ProfessionDto;
import com.subskill.service.ProfessionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/profession")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class ProfessionController {

    private final ProfessionService professionService;

    @Operation(summary = "Get all professions")
    @GetMapping("/all")
    List<ProfessionDto> getAllProfessions() {
        log.debug("get all professions");
        return professionService.findAll();
    }
}
