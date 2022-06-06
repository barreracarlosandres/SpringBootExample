package com.example.demo.common.domain.validations;

import com.example.demo.common.domain.exceptions.RuntimeExceptionNullValue;
import com.example.demo.common.domain.exceptions.RuntimeExceptionValue;

public class Validations {

    static public void validateValueGreaterThanZero(int value, String message) {
        if (value <= 0) {
            throw new RuntimeExceptionValue(message);
        }
    }

    static public void validateNotNull(String data, String message) {
        if(data == null || data.length() == 0)
        {
            throw new RuntimeExceptionNullValue(message);
        }
    }
}