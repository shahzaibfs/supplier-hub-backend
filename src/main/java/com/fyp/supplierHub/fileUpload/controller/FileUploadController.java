package com.fyp.supplierHub.fileUpload.controller;

import com.fyp.supplierHub.fileUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1.0/upload")
public class FileUploadController {

    private final FileUploadService fileUploadService ;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }



    @PostMapping("/file")
    public ResponseEntity<String> helloWorld (@RequestParam("file")MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return ResponseEntity.ok(fileUploadService.uploadFile(file)) ;
    }

}
