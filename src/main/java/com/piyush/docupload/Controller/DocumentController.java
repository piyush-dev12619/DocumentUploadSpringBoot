package com.piyush.docupload.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
@RequestMapping("/documents")

public class DocumentController {

    private final String uploadDir = "uploads/";


@PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> DocUpload(@RequestParam("file")MultipartFile file){

        try{

            if(file.isEmpty()){

                log.info("File is empty");
                return ResponseEntity.badRequest().body("File is empty");
            }

            // Create directory if it doesn't exist

            File dest = new File(uploadDir);

            if (!dest.exists()) {
                dest.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            log.info("Uploading file: {}", fileName);


// Complete path
            Path filePath = Path.of(uploadDir, fileName);
            log.info("File path: {}", filePath.toAbsolutePath().toString());

            //save file

            Files.write(filePath, file.getBytes());

            return ResponseEntity.ok("File uploaded successfully: " + fileName);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
