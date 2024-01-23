package com.subskill.controller;

import com.subskill.models.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("microskill")
@RequiredArgsConstructor
@Slf4j
public class MicroSkillController {
    @GetMapping("/add")
    public String addMicroSkill(){
        return null;
    }
    @PutMapping("/update")
    public String updateMicroSkill(){
        return null;
    }
    @GetMapping("/all")
    public List<Article> allArticles(){
        return null;
    }

}
