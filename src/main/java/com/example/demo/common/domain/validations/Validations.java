package com.example.demo.common.domain.validations;

import com.example.demo.common.domain.exceptions.RuntimeExceptionNullValue;
import com.example.demo.common.domain.exceptions.RuntimeExceptionValue;

public class Validations {

    private Validations() {
    }

    public static void valueGreaterThanZero(int value, String message) {
        if (value <= 0) {
            throw new RuntimeExceptionValue(message);
        }
    }

    public static void notNull(String data, String message) {
        if (data == null || data.length() == 0) {
            throw new RuntimeExceptionNullValue(message);
        }
    }
}