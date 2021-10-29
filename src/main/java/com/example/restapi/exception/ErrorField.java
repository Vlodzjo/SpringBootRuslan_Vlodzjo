package com.example.restapi.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder
public class ErrorField {

    private String property;
    private String message;

}
