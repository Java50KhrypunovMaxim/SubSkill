package com.subskill.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.subskill.exception.MicroSkillAlreadySavedException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;
import com.subskill.models.User;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.repository.SaveMicroskillRepository;
import com.subskill.service.SavedMicroskillService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaveMicroSkillServiceImplementation implements SavedMicroskillService {
	 private final MicroSkillRepository microSkillRepository;
	 private final UserRepository userRepository;
	 private final SaveMicroskillRepository  saveMicroskillRepository;
	
	@Override
	public SaveMicroskill addMicroSkillToUser(long user_id, long microskill_id) {
		boolean alreadySaved = saveMicroskillRepository.existsByUserAndMicroSkills(
	            userRepository.findById(user_id).orElseThrow(NoUserInRepositoryException::new),
	            microSkillRepository.findById(microskill_id).orElseThrow(MicroSkillNotFoundException::new)
	    );
	    if (alreadySaved) {
	        throw new MicroSkillAlreadySavedException("MicroSkill is already saved for the user");
	    }
		    MicroSkill microSkill = microSkillRepository.findById(microskill_id)
		            .orElseThrow(MicroSkillNotFoundException::new);
		    User user = userRepository.findById(user_id)
		            .orElseThrow(NoUserInRepositoryException::new);
		    SaveMicroskill saveMicroskill = new SaveMicroskill(user, microSkill);
		    saveMicroskillRepository.save(saveMicroskill);
		    return saveMicroskill;
		}

	@Override
	public void deleteMicroSkillFromUser(long user_id, long microskill_id) {
		Optional <SaveMicroskill> saveMicroskill = saveMicroskillRepository.findByUserAndMicroSkills(
	            userRepository.findById(user_id).orElseThrow(NoUserInRepositoryException::new),
	            microSkillRepository.findById(microskill_id).orElseThrow(MicroSkillNotFoundException::new));
		 saveMicroskillRepository.deleteById(saveMicroskill.get().getId());
	}

	@Override
	public List<MicroSkill> allMicroSkillsOfUser(long user_id) {
		 User user = userRepository.findById(user_id)
		            .orElseThrow(NoUserInRepositoryException::new);
		 List<SaveMicroskill> saveMicroskills = saveMicroskillRepository.findByUser(user);
		 return saveMicroskills.stream()
		            .map(SaveMicroskill::getMicroSkills)
		            .collect(Collectors.toList());
	}

}
