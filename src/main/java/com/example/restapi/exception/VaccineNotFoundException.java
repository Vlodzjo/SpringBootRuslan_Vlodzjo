package com.example.restapi.exception;

public class VaccineNotFoundException extends RuntimeException {

    public VaccineNotFoundException() {
    }

    public VaccineNotFoundException(String message) {
        super(message);
    }

}
