package com.example.football.controller;



import com.example.football.dto.ApiError;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> notFound(EntityNotFoundException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiError.builder()
                        .status(404).error("Not Found")
                        .message(ex.getMessage())
                        .timestamp(Instant.now())
                        .path(req.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> conflict(DataIntegrityViolationException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiError.builder()
                        .status(409).error("Conflict")
                        .message("Unique constraint violation or invalid data")
                        .timestamp(Instant.now())
                        .path(req.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> badRequest(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(
                ApiError.builder()
                        .status(400).error("Bad Request")
                        .message(msg)
                        .timestamp(Instant.now())
                        .path(req.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> other(Exception ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiError.builder()
                        .status(500).error("Internal Server Error")
                        .message("Unexpected error")
                        .timestamp(Instant.now())
                        .path(req.getRequestURI())
                        .build()
        );
    }
}

