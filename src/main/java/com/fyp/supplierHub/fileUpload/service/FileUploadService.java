package com.fyp.supplierHub.fileUpload.service;


import com.fyp.supplierHub.exceptions.Exceptions.BadRequestException;
import com.fyp.supplierHub.exceptions.Exceptions.FileNotUploadException;
import com.fyp.supplierHub.fileUpload.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileUploadService {

    private final FileUploadUtil fileUploadUtil ;

    @Autowired
    public FileUploadService(FileUploadUtil fileUploadUtil) {
        this.fileUploadUtil = fileUploadUtil;
    }

    public String uploadFile (MultipartFile file) {

        if(file.isEmpty()) throw new BadRequestException("Please Upload the File " , "api/v1.0/upload/file");
        if( !fileUploadUtil.isUploadFile(file)) throw new FileNotUploadException("File Upload Error on Server ", "api/v1.0/upload/file");

        String fileServingPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(file.getOriginalFilename())
                .toUriString();

        return fileServingPath;
    }
}
