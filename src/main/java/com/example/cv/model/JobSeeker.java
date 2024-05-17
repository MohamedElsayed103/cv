package com.example.cv.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JobSeeker {
    @Id
    private String id;

   /* @ManyToMany
    @JoinTable(name = "job_seeker_skills",
            joinColumns = @JoinColumn(name = "job_seeker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    *//*@Builder.Default*//*
    private List<Skill> skills ;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jobseeker_skills",
            joinColumns = @JoinColumn(name = "jobseeker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    /*@Builder.Default*/
    private Set<Skill> skills;

    private int experience;
    private String education;
    private String cv;
    private String jobTitle;

   /* public void addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getJobSeekers().add(this);
    }

    public void removeSkill(Skill skill) {
        this.skills.remove(skill);
        skill.getJobSeekers().remove(this);
    }
*/
}