package com.example.cv.model.dto;


import com.example.cv.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSkillToJobSeekerDto {
    private List<String> skills;
    private String JobSeekerId;

}
