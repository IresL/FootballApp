package com.example.football.dto;



import com.example.football.entity.Championship;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChampionshipResponse {
    private Long id;
    private String name;

    public static ChampionshipResponse from(Championship c) {
        return ChampionshipResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .build();
    }
}
