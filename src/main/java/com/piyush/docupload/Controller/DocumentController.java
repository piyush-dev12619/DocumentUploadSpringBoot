package com.piyush.docupload.Controller;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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




    @GetMapping("/download/{fileName}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadFile(
            @PathVariable String fileName) throws IOException {

log.info("Downloading file: {}", fileName);

        Path path = Paths.get(uploadDir).resolve(fileName);

        //Resource resource = new UrlResource(path.toUri());
        org.springframework.core.io.Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        log.info("File downloaded successfully: {}", fileName);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\""
                )

                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }
}
