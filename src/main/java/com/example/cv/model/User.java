package com.example.cv.model;

import com.example.cv.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User{
    @Id
    private String email;
    private String userName;
    private String password;
    private String id;
    private String address;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
}