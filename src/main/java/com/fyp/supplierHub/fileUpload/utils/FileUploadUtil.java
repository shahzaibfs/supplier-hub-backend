package com.fyp.supplierHub.fileUpload.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FileUploadUtil {
    public final String UPLOAD_DIR = new ClassPathResource("/static/image/").getFile().getAbsolutePath();

    public FileUploadUtil()throws IOException {
        System.out.println(new ClassPathResource("").getFile().getAbsolutePath());

    }

    public boolean  isUploadFile (MultipartFile file) {
        boolean isFileUploadSuccessfull  = false ;

        try {
            InputStream fileInputStream  = file.getInputStream();
            byte[] fileData =  new byte[fileInputStream.available()];

            fileInputStream.read(fileData);

            // ** write
            FileOutputStream fileOutputStream = new FileOutputStream(
                    UPLOAD_DIR+ File.separator+file.getOriginalFilename()
            );
            fileOutputStream.write(fileData);

            fileOutputStream.flush();
            fileOutputStream.close();

            isFileUploadSuccessfull = true ;

        }catch(Exception e){
            e.printStackTrace();
        }
        return isFileUploadSuccessfull;
    }
}
