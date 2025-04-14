package com.example.tmstraining.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({
            CartDetailsNotFoundException.class,
            CustomerNotFoundException.class,
            ItemNotFoundException.class,
            NotFoundException.class,
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            EmptyCartException.class,
            QuantityLessThanOneException.class,
            BadRequestException.class,
            ConstraintViolationException.class,
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException ex) {
        String message;
        if (ex.getCause() instanceof ConstraintViolationException) {
            message = ((ConstraintViolationException) ex.getCause()).getConstraintName();
        } else {
            message = ex.getMessage();
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            PaymentFailureException.class,
            IncorrectPasswordException.class
    })
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(RuntimeException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            UserExistedException.class
    })
    public ResponseEntity<ErrorResponse> handleForbiddenException(RuntimeException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        errorResponse.setError(HttpStatus.FORBIDDEN.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({
            ItemCascadeDeleteException.class
    })
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(RuntimeException ex) {
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
