package com.example.cv.model.dto;

import com.example.cv.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {
    private String email;
    private String userName;
    private String password;
    private String address;
    private String phone;
    private Role role;
}