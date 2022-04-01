package com.fyp.supplierHub.exceptions.Exceptions;

import java.time.LocalDate;

public class BadRequestException extends RuntimeException{
    private LocalDate timestamp =LocalDate.now();
    private String path;

    public BadRequestException(String message,String path) {
        super(message);
        this.path = path;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
