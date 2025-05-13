package com.example.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EcomErrorDetails.class)
    public ResponseEntity<ErrorResponse> handleEcomException(EcomErrorDetails ex) {
        // Creating error response based on the exception details
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorCode(),
                ex.getErrorMessage(),
                ex.getMessage()
        );

        // Returning a bad request response (you can choose different status codes based on the error)
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}