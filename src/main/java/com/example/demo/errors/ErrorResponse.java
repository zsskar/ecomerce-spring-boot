package com.example.demo.errors;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    @Getter
    @Setter
    private int errorCode;
    @Getter @Setter
    private String errorMessage;
    @Getter @Setter
    private String message;
}