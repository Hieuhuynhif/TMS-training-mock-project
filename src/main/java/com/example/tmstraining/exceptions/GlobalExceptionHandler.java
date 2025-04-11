package com.example.tmstraining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            CartDetailsNotFoundException.class,
            CustomerNotFoundException.class,
            ItemNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(CartDetailsNotFoundException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            EmptyCartException.class,
            QuantityLessThanOneException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(EmptyCartException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            PaymentFailureException.class,
            IncorrectPasswordException.class
    })
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(PaymentFailureException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {
            UserExistedException.class
    })
    public ResponseEntity<ErrorResponse> handleForbiddenException(UserExistedException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        errorResponse.setError(HttpStatus.FORBIDDEN.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {
            ItemCascadeDeleteError.class
    })
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(ItemCascadeDeleteError ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
