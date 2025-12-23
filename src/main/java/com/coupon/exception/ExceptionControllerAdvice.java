package com.coupon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CouponInvalidException.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(CouponInvalidException ex, WebRequest request) {
        ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
    }
}
