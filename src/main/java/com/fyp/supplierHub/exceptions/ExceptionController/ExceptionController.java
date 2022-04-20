package com.fyp.supplierHub.exceptions.ExceptionController;

import com.fyp.supplierHub.exceptions.Exceptions.*;
import com.fyp.supplierHub.exceptions.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionResponse> throwNotFoundException(NotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(value = UniqueColumnException.class)
    public ResponseEntity<ExceptionResponse> throwUniqueColumnException(UniqueColumnException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionResponse> throwBadRequestException(BadRequestException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<ExceptionResponse> throwDatabaseException(DatabaseException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(value = FileNotUploadException.class)
    public ResponseEntity<ExceptionResponse> throwFileNotUploadException(FileNotUploadException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

}
