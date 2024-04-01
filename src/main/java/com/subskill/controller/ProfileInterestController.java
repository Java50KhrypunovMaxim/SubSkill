package com.subskill.controller;

import com.subskill.dto.InterestDto;
import com.subskill.service.ProfileInterestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interest")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class ProfileInterestController {
    private final ProfileInterestService profileInterestService;

    @Operation(summary = "Show all interest")
    @GetMapping("/all")
    public List<InterestDto> showAllInterest() {
        return profileInterestService.showAllProfileInterest();
    }


    @Operation(summary = "Delete interest by id")
    @DeleteMapping("/{id}")
    void deleteInterest(@PathVariable Long id) {
        log.debug("delete interest : interest with id {}", id);
        profileInterestService.deleteProfileInterest(id);
    }
}
