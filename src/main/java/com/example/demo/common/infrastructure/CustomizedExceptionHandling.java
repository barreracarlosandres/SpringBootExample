package com.example.demo.common.infrastructure;

import com.example.demo.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.demo.common.domain.exceptions.RuntimeExceptionNullValue;
import com.example.demo.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.demo.common.domain.exceptions.RuntimeExceptionValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeExceptionValue.class)
    public ResponseEntity<ExceptionResponse> exceptionValue(RuntimeExceptionValue exception) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeExceptionExistValue.class)
    public ResponseEntity<ExceptionResponse> exceptionExistValue(RuntimeExceptionExistValue exception) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(RuntimeExceptionNullPost.class)
    public ResponseEntity<ExceptionResponse> exceptionNullPost(RuntimeExceptionNullPost exception) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeExceptionNullValue.class)
    public ResponseEntity<ExceptionResponse> exceptionNullValue(RuntimeExceptionNullValue exception) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
