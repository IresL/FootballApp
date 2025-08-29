package com.example.football.dto;



import com.example.football.entity.Team;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TeamResponse {
    private Long id;
    private Long championshipId;
    private String name;
    private int titlesCount;

    public static TeamResponse from(Team t) {
        return TeamResponse.builder()
                .id(t.getId())
                .championshipId(t.getChampionship().getId())
                .name(t.getName())
                .titlesCount(t.getTitlesCount())
                .build();
    }
}

