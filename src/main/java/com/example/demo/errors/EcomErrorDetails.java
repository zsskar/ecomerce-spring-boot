package com.example.demo.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EcomErrorDetails extends RuntimeException {

    private int errorCode;
    private String errorMessage;
    private String message;

    public EcomErrorDetails(int errorCode, String errorMessage, String message) {
        super(message); // Passing the message to the superclass
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.message = message;
    }
}
