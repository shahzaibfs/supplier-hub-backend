package com.fyp.supplierHub.exceptions.Exceptions;

import java.time.LocalDate;

public class NotFoundException extends RuntimeException{

    private LocalDate createdAt;
    private  String error ;
    private  String helpingMessage ;

    public NotFoundException(String error, String helpingMessage) {
        super(error);
        this.error = error;
        this.helpingMessage = helpingMessage;
        this.createdAt=LocalDate.now();

    }

    public NotFoundException (String error){
        super(error);
        this.error = error;
        this.createdAt=LocalDate.now();
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
