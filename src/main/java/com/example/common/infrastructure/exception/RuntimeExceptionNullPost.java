package com.example.common.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RuntimeExceptionNullPost extends RuntimeException {

    private final String message;
}