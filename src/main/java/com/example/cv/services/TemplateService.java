package com.example.cv.services;

import com.example.cv.model.Template;
import com.example.cv.repository.TemplateRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@AllArgsConstructor
@Service
@Data
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final UserService userService;

    private final String IMAGE_FOLDER_PATH ="D:/projects/cvProject/cv/images/";
    private final String FILE_FOLDER_PATH ="D:/projects/cvProject/cv/files/";

    public String uploadTemplate(MultipartFile file, MultipartFile file2, String userEmail) throws IOException {
        if (!userService.isUserAdmin(userEmail))
            throw new RuntimeException("User is not admin");

        String imagePath = IMAGE_FOLDER_PATH+ file.getOriginalFilename();
        String filePath = FILE_FOLDER_PATH+ file2.getOriginalFilename();

        Template template = Template.builder()
                .templateName(file2.getOriginalFilename())
                .templateImagePath(imagePath)
                .templateFilePath(filePath)
                .build();

        file.transferTo(new File(imagePath));
        file2.transferTo(new File(filePath));
        templateRepository.save(template);

        return "file uploaded successfully: "+ filePath+ "\nand image uploaded successfully: "+imagePath;
    }
    public byte[] downloadTemplate(String fileName) throws IOException {

        Optional<Template> template = templateRepository.findByTemplateName(fileName);

        String filepath = template.get().getTemplateFilePath();

        return Files.readAllBytes(new File(filepath).toPath());
    }



}
