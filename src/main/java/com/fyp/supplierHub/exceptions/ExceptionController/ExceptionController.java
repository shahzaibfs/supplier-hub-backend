package com.fyp.supplierHub.exceptions.ExceptionController;

import com.fyp.supplierHub.exceptions.Exceptions.BadRequestException;
import com.fyp.supplierHub.exceptions.Exceptions.DatabaseException;
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
                exception.getTimestamp(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(value = UniqueColumnException.class)
    public ResponseEntity<?> throwUniqueColumnException(UniqueColumnException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> throwBadRequestException(BadRequestException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<?> throwDatabaseException(DatabaseException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getTimestamp(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                exception.getPath()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }


}
