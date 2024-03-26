package com.example.testproject.exception;

public class UnavailableWeatherServiceException extends RuntimeException{

    public UnavailableWeatherServiceException(String message) {
        super(message);
    }
}
