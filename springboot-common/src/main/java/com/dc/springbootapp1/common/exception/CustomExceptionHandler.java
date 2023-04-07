package com.dc.springbootapp1.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity globalExceptionHandler(Exception exception) {
        return new ResponseEntity<>("Something went wrong: " + exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
