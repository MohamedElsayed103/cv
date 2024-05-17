package com.example.cv.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "skills")
   /* @Builder.Default*/
    private Set<JobSeeker> jobSeekers;

    public Skill(String skillName) {
        name = skillName;
    }
}
