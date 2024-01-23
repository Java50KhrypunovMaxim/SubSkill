package com.subskill.microSkillService;

import com.subskill.models.Article;
import com.subskill.models.MicroSkill;

import java.util.List;

public interface MicroSkillService  {
        List<Article> getAllArticle();
        MicroSkill getArticle();
        MicroSkill getMicroSkillId();
        MicroSkill createMicroSkill();
        MicroSkill deleteMicroSkill();
        MicroSkill findByTechnology();
}
