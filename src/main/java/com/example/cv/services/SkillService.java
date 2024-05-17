package com.example.cv.services;

import com.example.cv.model.Skill;
import com.example.cv.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public void addSkill(Skill skill){

        Skill skill1 = Skill.builder()
                .name(skill.getName())
                .build();
        skillRepository.save(skill1);

    }

}
