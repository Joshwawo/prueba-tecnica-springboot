package com.example.crudspring.errorHandler;

public class ErrorHandInscripcion extends RuntimeException {
    private ErrorResponseMio errorResponse;

    public ErrorHandInscripcion(String message, int httpStatus) {
        super( message);
        errorResponse = new ErrorResponseMio();
        errorResponse.setMessage(message);
        errorResponse.setStatus(httpStatus);
        errorResponse.setTimeStamp(java.time.LocalTime.now());
    }

    public ErrorResponseMio getErrorResponseInscripcion() {
        return errorResponse;
    }



}
