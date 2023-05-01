package com.example.crudspring.errorHandler;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ErrorResponseMio extends Throwable {
    private int status;
    private String message;
    private LocalTime timeStamp;
}
