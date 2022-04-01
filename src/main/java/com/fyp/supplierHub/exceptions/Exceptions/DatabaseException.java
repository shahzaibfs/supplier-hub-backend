package com.fyp.supplierHub.exceptions.Exceptions;

import java.awt.event.FocusEvent;
import java.time.LocalDate;

public class DatabaseException extends RuntimeException{

    private LocalDate timestamp =LocalDate.now();
    private String path;

    public DatabaseException(String message, String path) {
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
