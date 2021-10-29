package com.example.restapi.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Data
@SuperBuilder
public class CustomErrorFieldResponse extends AbstractCustomErrorResponse {

    private List<ErrorField> errorMessages;
}
