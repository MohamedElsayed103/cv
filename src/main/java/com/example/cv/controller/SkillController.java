package com.example.cv.controller;

import com.example.cv.model.Skill;
import com.example.cv.model.dto.UserSignUpDto;
import com.example.cv.services.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@AllArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/add")
    public ResponseEntity addSkill(@RequestBody Skill skill) {
        skillService.addSkill(skill);
        return ResponseEntity.ok().build();
    }
}
