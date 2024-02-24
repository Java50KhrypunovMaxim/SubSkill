package com.subskill.service;

import java.util.List;

import com.subskill.dto.CartDto;
import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;

public interface SavedMicroskillService {
	    SaveMicroskill addMicroSkillToUser(long user_id, long microskill_id);
	    void deleteMicroSkillFromUser(long user_id, long microskill_id);
	    List<MicroSkill> allMicroSkillsOfUser(long user_id);
}
