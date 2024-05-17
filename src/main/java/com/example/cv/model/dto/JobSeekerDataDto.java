package com.example.cv.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerDataDto {

    private String id;
    private int experience;
    private String education;
    private String jobTitle;
    private Set<SkillDto> skills;
}
