package com.example.cv.model.dto;

import com.example.cv.model.Skill;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditJobSeekerProfileDto {

    private String id;

    private List<Skill> skills;
    private int experience;
    private String education;
    private String cv;
    private String jobTitle;

}
