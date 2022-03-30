package com.fyp.supplierHub.exceptions.models;

import java.time.LocalDate;

public class ExceptionResponse {
    private LocalDate createdAt ;
    private String error ;
    private String helpingMessage;

    public ExceptionResponse(LocalDate localDate, String error, String helpingMessage) {
        this.createdAt = localDate;
        this.error = error;
        this.helpingMessage = helpingMessage;
    }

    public ExceptionResponse() {
    }

    public LocalDate getLocalDate() {
        return createdAt;
    }

    public void setLocalDate(LocalDate localDate) {
        this.createdAt = localDate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getHelpingMessage() {
        return helpingMessage;
    }

    public void setHelpingMessage(String helpingMessage) {
        this.helpingMessage = helpingMessage;
    }
}
