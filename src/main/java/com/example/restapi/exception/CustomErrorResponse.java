package com.example.restapi.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder
public class CustomErrorResponse extends AbstractCustomErrorResponse {

    private String errorMessage;

}
