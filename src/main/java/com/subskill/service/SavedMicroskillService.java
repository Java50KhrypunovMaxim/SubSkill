package com.subskill.service;

import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;

import java.util.List;

public interface SavedMicroskillService {
	    SaveMicroskill addMicroSkillToUser(long user_id, long microskill_id);
	    void deleteMicroSkillFromUser(long user_id, long microskill_id);
	    List<MicroSkill> allMicroSkillsOfUser(long user_id);
}
