package com.example.football.dto;



import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TeamCreateRequest {
    private String name;
    private int titlesCount;
}

