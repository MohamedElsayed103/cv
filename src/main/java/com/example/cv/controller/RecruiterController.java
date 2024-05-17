package com.example.cv.controller;

import com.example.cv.model.JobSeeker;
import com.example.cv.model.Recruiter;
import com.example.cv.model.dto.JobSeekerDataDto;
import com.example.cv.model.dto.UserEmailDto;
import com.example.cv.services.RecruiterService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hr")
@AllArgsConstructor
public class RecruiterController {

    private final RecruiterService recruiterService;

    /*@PostMapping(value = "/skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchJobSeekersBySkills(@RequestBody List<String> skills) throws IOException {

        return ResponseEntity.ok().body(recruiterService.findJobSeekersBySkills(skills));
    }*/

    @GetMapping("/skills")
    public List<JobSeekerDataDto> getJobSeekersBySkills(@RequestParam List<String> skillNames) {
        return recruiterService.findJobSeekersBySkills(skillNames);
    }

    @PostMapping(value = "/edu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity filterJobSeekersByEducation(@RequestParam("edu") String educationLevel) throws IOException {

        return ResponseEntity.ok().body(recruiterService.filterJobSeekersByEducation(educationLevel));
    }
    @PostMapping(value = "/jobTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity filterJobSeekersByJobTitle(@RequestParam("jobTitle") String jobTitle) throws IOException {

        return ResponseEntity.ok().body(recruiterService.searchJobSeekersByJobTitle(jobTitle));
    }
    @PostMapping(value = "/exp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity filterJobSeekersByExperience(@RequestParam("min") int minExperience,@RequestParam("max") int maxExperience) throws IOException {

        return ResponseEntity.ok().body(recruiterService.filterJobSeekersByExperience(minExperience,maxExperience));
    }
    @PostMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reviewJobSeekerProfile(@RequestBody UserEmailDto userEmail) throws IOException {

        return ResponseEntity.ok().body(recruiterService.reviewJobSeekerProfile(userEmail));
    }

    @PostMapping(value = "/editProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editProfile(@RequestBody Recruiter recruiter) throws IOException {
        return ResponseEntity.ok().body(recruiterService.editProfile(recruiter));
    }

}
