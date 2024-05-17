package com.example.cv.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String templateName;
    private String templateFilePath;
    private String templateImagePath;
}