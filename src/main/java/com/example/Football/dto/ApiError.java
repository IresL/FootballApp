package com.example.Football.dto;



import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiError {
    private int status;
    private String error;
    private String message;
    private Instant timestamp;
    private String path;
}

