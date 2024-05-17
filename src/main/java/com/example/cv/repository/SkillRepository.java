package com.example.cv.repository;

import com.example.cv.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill,Long> {

    Optional<Skill> findByName(String name);
}
