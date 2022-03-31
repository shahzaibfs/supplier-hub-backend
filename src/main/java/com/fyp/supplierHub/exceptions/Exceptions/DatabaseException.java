package com.fyp.supplierHub.exceptions.Exceptions;

import java.awt.event.FocusEvent;
import java.time.LocalDate;

public class DatabaseException extends RuntimeException{

    private LocalDate createdAt = LocalDate.now();
    private  String error ;
    private Throwable cause ;

    public DatabaseException(String error,Throwable cause ) {
        super(error, cause);
        this.error = error;
        this.cause = cause;
    }

    public DatabaseException(String error) {
        super(error);
        this.error = error;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getError() {
        return error;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
