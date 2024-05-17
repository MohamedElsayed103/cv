package com.example.cv.repository;

import com.example.cv.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker,String> {
    Optional<JobSeeker> findById(String userEmail);


    /*@Query("SELECT DISTINCT j FROM JobSeeker j JOIN j.skills s WHERE s.name IN :skillNames")
    List<JobSeeker> findBySkills(@Param("skillNames") List<String> skillNames);*/
    @Query("SELECT js FROM JobSeeker js JOIN js.skills s WHERE s.name IN :skillNames GROUP BY js HAVING COUNT(s) = :size")
    List<JobSeeker> findBySkills(@Param("skillNames") List<String> skillNames, @Param("size") long size);


    // List<JobSeeker> findBySkills(List<String> skills);

    List<JobSeeker> findByEducationIgnoreCase(String educationLevel);
    List<JobSeeker> findByJobTitleIgnoreCase(String jobTitle);
    List<JobSeeker> findByExperienceBetween(int minExperience, int maxExperience);

}