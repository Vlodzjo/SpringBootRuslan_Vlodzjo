package com.example.restapi.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    protected ResponseEntity<CustomErrorResponse> handleNotFoundException(UserAlreadyExistsException ex, WebRequest request) {
        return getCustomErrorResponseResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, PSQLException.class})
    protected ResponseEntity<CustomErrorResponse> handleBadRequestException(Exception ex) {
        return getCustomErrorResponseResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<CustomErrorFieldResponse> handleConstraintViolationException(ConstraintViolationException e) {
        List<ErrorField> errorMessages = new ArrayList<>();
        for (ConstraintViolation<?> error : e.getConstraintViolations()) {
            ErrorField build = ErrorField
                    .builder()
                    .property(error.getPropertyPath().toString())
                    .message(error.getMessage())
                    .build();
            errorMessages.add(build);
        }
        return createCustomErrorFieldResponse(HttpStatus.BAD_REQUEST, errorMessages);
    }

    private ResponseEntity<CustomErrorFieldResponse> createCustomErrorFieldResponse(HttpStatus httpStatus, List<ErrorField> errorMessages) {
        return new ResponseEntity<>(
                CustomErrorFieldResponse.builder()
                        .errorCode(httpStatus.getReasonPhrase())
                        .errorMessages(errorMessages)
                        .status(httpStatus.value())
                        .timestamp(LocalDateTime.now()).build()
                , httpStatus);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<CustomErrorResponse> handleNotFoundException(UserNotFoundException ex, WebRequest a) {
        return getCustomErrorResponseResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<CustomErrorResponse> getCustomErrorResponseResponseEntity(Exception ex, HttpStatus httpStatus) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setErrorCode(httpStatus.getReasonPhrase());
        customErrorResponse.setStatus(httpStatus.value());
        customErrorResponse.setErrorMessage(ex.getMessage());
        customErrorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, httpStatus);
    }
}
