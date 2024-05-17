package com.example.cv.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditContactInformationDto {

    private String userEmail;
    private String address;
    private String userName;
    private String phone;
    private String password;
}
