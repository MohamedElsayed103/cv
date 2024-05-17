package com.example.cv.services;

import com.example.cv.model.JobSeeker;
import com.example.cv.model.Skill;
import com.example.cv.model.dto.AddSkillToJobSeekerDto;
import com.example.cv.model.dto.UploadCvDto;
import com.example.cv.model.dto.UserEmailDto;
import com.example.cv.repository.JobSeekerRepository;
import com.example.cv.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;
    private final SkillRepository skillRepository;
    private final SkillService skillService;

    public void uploadCv(MultipartFile file , String userEmail) throws IOException {

        String FILE_FOLDER_PATH = "D:/projects/cvProject/cv/jobSeekersCvs/";
        JobSeeker jobSeeker = jobSeekerRepository.findById(userEmail).get();


        String filePath = FILE_FOLDER_PATH + jobSeeker.getId()+".pdf";
        jobSeeker.setCv(filePath);

        file.transferTo(new File(filePath));
        jobSeekerRepository.save(jobSeeker);
    }
    public void deleteCv(UserEmailDto userEmail) throws IOException {

        JobSeeker jobSeeker = jobSeekerRepository.findById(userEmail.getUserEmail()).get();
        String cvPath = jobSeeker.getCv();
        Files.deleteIfExists(new File(cvPath).toPath());
        jobSeeker.setCv("");
        jobSeekerRepository.save(jobSeeker);
    }
    public byte[] viewCv(UserEmailDto userEmail) throws IOException {
        JobSeeker jobSeeker = jobSeekerRepository.findById(userEmail.getUserEmail()).get();
        String cvPath = jobSeeker.getCv();

        return Files.readAllBytes(new File(cvPath).toPath());
    }

    public JobSeeker editProfile(JobSeeker jobSeeker ){
        JobSeeker jobSeeker2 = jobSeekerRepository.findById(jobSeeker.getId()).get();

        jobSeeker2.setJobTitle(jobSeeker.getJobTitle());
        jobSeeker2.setEducation(jobSeeker.getEducation());
        jobSeeker2.setSkills(jobSeeker2.getSkills());
        jobSeeker2.setExperience(jobSeeker.getExperience());

        return jobSeekerRepository.save(jobSeeker2);
    }
    /*public void addSkillsToJobSeeker( AddSkillToJobSeekerDto skills) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(skills.getJobSeekerId())
                .orElseThrow(() -> new IllegalArgumentException("Job seeker not found with id: " + skills.getJobSeekerId()));

        for (Skill skill : skills.getSkills()) {
            Skill existingSkill = skillRepository.findByName(skill.getName());
            if (existingSkill != null) {
                jobSeeker.addSkill(existingSkill);
            } else {
                skillService.addSkill(skill);
                skillRepository.save(skill);
                jobSeeker.addSkill(skill);
            }
        }
        jobSeekerRepository.save(jobSeeker);
    }*/
    public JobSeeker addSkillsToJobSeeker(AddSkillToJobSeekerDto addSkills) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(addSkills.getJobSeekerId())
                .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));

        Set<Skill> skills = new HashSet<>();
        for (String skillName : addSkills.getSkills()) {
            Skill skill = skillRepository.findByName(skillName)
                    .orElseGet(() -> skillRepository.save(new Skill(skillName)));
            skills.add(skill);
        }

        jobSeeker.getSkills().addAll(skills);
        return jobSeekerRepository.save(jobSeeker);
    }

   /* public void deleteSkillsFromJobSeeker(AddSkillToJobSeekerDto skills) {
        Optional<JobSeeker> optionalJobSeeker = jobSeekerRepository.findById(skills.getJobSeekerId());
        if (optionalJobSeeker.isPresent()) {
            JobSeeker jobSeeker = optionalJobSeeker.get();
            for (Skill skill : skills.getSkills()) {
                Skill optionalSkill = skillRepository.findByName(skill.getName()).;
                if (optionalSkill!= null) {
                    jobSeeker.removeSkill(skill);
                } else {
                    throw new IllegalArgumentException("Skill not found with id: " + skill.getId());
                }
            }
            jobSeekerRepository.save(jobSeeker);
        } else {
            throw new IllegalArgumentException("Job seeker not found with id: " + skills.getJobSeekerId());
        }*/
   public JobSeeker deleteSkillsFromJobSeeker(AddSkillToJobSeekerDto deleteSkill) {
       JobSeeker jobSeeker = jobSeekerRepository.findById(deleteSkill.getJobSeekerId())
               .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));

       Set<Skill> skills = new HashSet<>();
       for (String skillName : deleteSkill.getSkills()) {
           Skill skill = skillRepository.findByName(skillName)
                   .orElse(null);
           if (skill != null) {
               skills.add(skill);
           }
       }

       jobSeeker.getSkills().removeAll(skills);
       return jobSeekerRepository.save(jobSeeker);
   }
}






