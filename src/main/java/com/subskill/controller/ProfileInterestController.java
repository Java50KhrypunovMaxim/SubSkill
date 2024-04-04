package com.subskill.controller;

import com.subskill.dto.InterestDto;
import com.subskill.models.Interest;
import com.subskill.repository.ProfileInterestRepository;
import com.subskill.service.ProfileInterestService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final ProfileInterestRepository profileInterestRepository;

    @Operation(summary = "Show all interest")
    @GetMapping("/all")
    public List<String> showAllInterest() {
        return profileInterestService.showAllProfileInterest();
    }
    @Operation(summary = "Show all interest")

    @PostMapping("/add/{tags}")
    public  List<InterestDto> addInterest(@PathVariable String tags) {
        return profileInterestService.addInterestToUser(tags);
    }
    @PostMapping("/add")
    public Interest addInt(@RequestBody InterestDto interestDto){
        Interest interest = new Interest(interestDto.name());
        return profileInterestRepository.save(interest);
    }

    @Operation(summary = "Delete interest by id")
    @DeleteMapping("/{id}")
    void deleteInterest(@PathVariable Long id) {
        log.debug("delete interest : interest with id {}", id);
        profileInterestService.deleteProfileInterest(id);
    }
}
