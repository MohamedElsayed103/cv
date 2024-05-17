package com.example.cv.controller;

import com.example.cv.model.User;
import com.example.cv.model.dto.LoginRequestDto;
import com.example.cv.model.dto.UserEditContactInformationDto;
import com.example.cv.model.dto.UserSignUpDto;
import com.example.cv.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signUp(@RequestBody UserSignUpDto user) {
        userService.signUp(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> logIn(@RequestBody LoginRequestDto loginDto) {
        return ResponseEntity.ok(userService.logIn(loginDto));
    }
    @PostMapping(value = "/edit")
    public ResponseEntity<User> editInformation(@RequestBody UserEditContactInformationDto user ) {
        return ResponseEntity.ok(userService.editContactInformation(user));
    }

}
