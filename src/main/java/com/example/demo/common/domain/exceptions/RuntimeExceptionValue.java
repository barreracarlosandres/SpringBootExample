package com.example.demo.common.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RuntimeExceptionValue extends RuntimeException {

    private final String message;
}

