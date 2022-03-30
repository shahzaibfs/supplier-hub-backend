package com.fyp.supplierHub.exceptions.Exceptions;

import java.time.LocalDate;

public class UniqueColumnException extends RuntimeException{
    private LocalDate createdAt;
    private  String error ;
    private  String helpingMessage ;

    public UniqueColumnException( String error, String helpingMessage) {
        super(error);
        this.createdAt=LocalDate.now();
        this.error = error;
        this.helpingMessage = helpingMessage;
    }

    public UniqueColumnException(String error) {
        super(error);
        this.error = error;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getError() {
        return error;
    }

    public String getHelpingMessage() {
        return helpingMessage;
    }
}
