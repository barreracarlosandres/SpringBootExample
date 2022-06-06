package com.example.demo.common.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RuntimeExceptionExistValue extends RuntimeException {

    private String message;
}
