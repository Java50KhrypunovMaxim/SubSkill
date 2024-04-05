package com.subskill.controller;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;
import com.subskill.models.Interest;
import com.subskill.models.User;
import com.subskill.repository.ProfileInterestRepository;
import com.subskill.service.ProfileInterestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/interest")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class ProfileInterestController {
    private final ProfileInterestService profileInterestService;
    private final ProfileInterestRepository profileInterestRepository;

    @Operation(summary = "Show all interest")
    @GetMapping("/all")
    public List<InterestDto> showAllInterest() {
      return  profileInterestService.showAllProfileInterest();
    }

    @PostMapping("/add")
    void setInterests( @RequestBody List<Tags> interests) {
         profileInterestService.setInterests(interests);
    }


    @Operation(summary = "Delete interest by id")
    @DeleteMapping("/{tags}")
    void deleteInterest(@PathVariable Tags tags) {
        log.debug("delete interest : interest with id {}", tags);
        profileInterestService.deleteProfileInterest(tags);
    }
}
