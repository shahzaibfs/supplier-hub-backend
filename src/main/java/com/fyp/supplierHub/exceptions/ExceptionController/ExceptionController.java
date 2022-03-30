package com.fyp.supplierHub.exceptions.ExceptionController;

import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import com.fyp.supplierHub.exceptions.Exceptions.UniqueColumnException;
import com.fyp.supplierHub.exceptions.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> throwNotFoundException(NotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getCreatedAt(),
                exception.getError(),
                exception.getHelpingMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(value = UniqueColumnException.class)
    public ResponseEntity<?> throwUniqueColumnException(UniqueColumnException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getCreatedAt(),
                exception.getError(),
                exception.getHelpingMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}
