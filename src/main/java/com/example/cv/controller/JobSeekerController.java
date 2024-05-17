package com.example.cv.controller;


import com.example.cv.model.JobSeeker;
import com.example.cv.model.dto.AddSkillToJobSeekerDto;
import com.example.cv.model.dto.UploadCvDto;
import com.example.cv.model.dto.UserEmailDto;
import com.example.cv.model.dto.UserSignUpDto;
import com.example.cv.services.JobSeekerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/seek")
@AllArgsConstructor
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    @PostMapping(value = "/uploadCv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadCv(@RequestPart("file") MultipartFile file,@RequestPart("email") String userEmail) throws IOException {
        jobSeekerService.uploadCv(file,userEmail);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/deleteCv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCv( @RequestBody UserEmailDto userEmail) throws IOException {
        jobSeekerService.deleteCv(userEmail);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/viewCv", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewCv( @RequestBody UserEmailDto userEmail) throws IOException {

        return ResponseEntity.ok().body(jobSeekerService.viewCv(userEmail));
    }
    @PostMapping(value = "/editProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editProfile(@RequestBody JobSeeker jobSeeker) throws IOException {
        return ResponseEntity.ok().body(jobSeekerService.editProfile(jobSeeker));
    }
    @PostMapping(value = "/addSkills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSkillsToJobSeeker(@RequestBody AddSkillToJobSeekerDto skills) throws IOException {

        jobSeekerService.addSkillsToJobSeeker(skills);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/deleteSkills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSkillsFromJobSeeker(@RequestBody AddSkillToJobSeekerDto skills) throws IOException {

        jobSeekerService.deleteSkillsFromJobSeeker(skills);
        return ResponseEntity.ok().build();
    }
}











