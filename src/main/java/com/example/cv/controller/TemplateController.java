package com.example.cv.controller;

import com.example.cv.model.dto.UserSignUpDto;
import com.example.cv.services.TemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/temp")
@AllArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping(value = "/uploadTemp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadTemplate(@RequestParam("image") MultipartFile file,@RequestParam("file") MultipartFile file2, @RequestParam("email") String userEmail) throws IOException {
        return ResponseEntity.ok().body(templateService.uploadTemplate(file,file2,userEmail));
    }
    @GetMapping(value = "/downloadTemp")
    public ResponseEntity downloadTemplate(@RequestParam("fileName") String fileName) throws IOException {
        return ResponseEntity.ok().body(templateService.downloadTemplate(fileName));
    }
}