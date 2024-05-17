package com.example.cv.services;

import com.example.cv.model.JobSeeker;
import com.example.cv.model.Recruiter;
import com.example.cv.model.Skill;
import com.example.cv.model.dto.JobSeekerDataDto;
import com.example.cv.model.dto.SkillDto;
import com.example.cv.model.dto.UserEmailDto;
import com.example.cv.repository.JobSeekerRepository;
import com.example.cv.repository.RecruiterRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    private final JobSeekerRepository jobSeekerRepository;

    // Method to search for job seekers by skills
   /* public List<JobSeeker> searchJobSeekersBySkills(List<String> skills) {
        return jobSeekerRepository.findBySkills(skills);
    }*/

    @Transactional
    public List<JobSeekerDataDto> findJobSeekersBySkills(List<String> skillNames) {
        List<JobSeeker> jobSeekers = jobSeekerRepository.findBySkills(skillNames, skillNames.size());
        return jobSeekers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private JobSeekerDataDto mapToDTO(JobSeeker jobSeeker) {
        JobSeekerDataDto dto = new JobSeekerDataDto();
        dto.setId(jobSeeker.getId());
        dto.setExperience(jobSeeker.getExperience());
        dto.setEducation(jobSeeker.getEducation());
        dto.setJobTitle(jobSeeker.getJobTitle());
        dto.setSkills(jobSeeker.getSkills().stream().map(this::mapToDTO2).collect(Collectors.toSet()));
        return dto;
    }

    private SkillDto mapToDTO2(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setName(skill.getName());
        return dto;
    }


    public List<JobSeeker> filterJobSeekersByEducation(String educationLevel) {
        return jobSeekerRepository.findByEducationIgnoreCase(educationLevel);
    }

    public List<JobSeeker> searchJobSeekersByJobTitle(String jobTitle) {
        return jobSeekerRepository.findByJobTitleIgnoreCase(jobTitle);
    }

    public List<JobSeeker> filterJobSeekersByExperience(int minExperience, int maxExperience) {
        return jobSeekerRepository.findByExperienceBetween(minExperience, maxExperience);
    }


    public JobSeeker reviewJobSeekerProfile(UserEmailDto userEmail) {
        return jobSeekerRepository.findById(userEmail.getUserEmail()).orElse(null);
    }

    public Recruiter editProfile(Recruiter recruiter ){
        Recruiter recruiter1 = recruiterRepository.findById(recruiter.getId()).get();

        recruiter1.setCompanyName(recruiter.getCompanyName());
        recruiter1.setIndustry(recruiter.getIndustry());

        return recruiterRepository.save(recruiter1);
    }

}
