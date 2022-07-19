package com.example.common.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RuntimeExceptionNullValue extends RuntimeException {

    private final String message;
}
