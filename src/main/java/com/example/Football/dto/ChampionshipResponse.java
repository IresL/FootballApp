package com.example.Football.dto;



import com.example.Football.entity.Championship;
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
