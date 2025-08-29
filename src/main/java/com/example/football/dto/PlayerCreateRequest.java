package com.example.football.dto;



import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlayerCreateRequest {
    private String fullName;
    private int number;
    private String position;   // Admin მიუთითებს
    private int age;
    private String country;
    private BigDecimal price;
}

