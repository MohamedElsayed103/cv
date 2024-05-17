package com.example.cv.repository;

import com.example.cv.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Integer> {
    Optional<Template> findByTemplateName(String fileName);
}
