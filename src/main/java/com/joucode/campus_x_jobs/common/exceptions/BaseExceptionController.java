package com.joucode.campus_x_jobs.common.exceptions;

import com.joucode.campus_x_jobs.common.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class BaseExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        String requestedUrl = request.getRequestURL().toString();
        Response response = new Response(NOT_FOUND.value(), NOT_FOUND.name(), Map.of("error", Map.of("path", requestedUrl)));
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidateExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        Response response = new Response(UNPROCESSABLE_ENTITY.value(), UNPROCESSABLE_ENTITY.name(), Map.of("error", errors));

        return new ResponseEntity<>(response, UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<Response> handleNotFoundException(NotFoundException ex) {
        Response response = new Response(NOT_FOUND.value(), NOT_FOUND.name(), Map.of("error", ex.getMessage()));
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(NotAuthorizationInvalidException.class)
    private ResponseEntity<Response> handleCredentialsInvalidException(NotAuthorizationInvalidException ex){
        Response response = new Response(UNAUTHORIZED.value(), UNAUTHORIZED.name(), Map.of("error", ex.getMessage()));
        return new ResponseEntity<>(response, UNAUTHORIZED);
    }

    @ExceptionHandler(GlobalException.class)
    private ResponseEntity<Response> handleGlobalException(GlobalException ex){
        Response response = new Response(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.name(), Map.of("error", ex.getMessage()));
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<Object> handleCustomAuthenticationException(CustomAuthenticationException ex) {

        Response response = new Response(UNAUTHORIZED.value(), UNAUTHORIZED.name(), Map.of("error", ex.getMessage()));

        return new ResponseEntity<>(response, UNAUTHORIZED);
    }
}