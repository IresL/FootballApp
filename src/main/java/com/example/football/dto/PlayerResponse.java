package com.example.football.dto;



import com.example.football.entity.Player;
import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlayerResponse {
    private Long id;
    private Long teamId;
    private String fullName;
    private int number;
    private String position;
    private int age;
    private String country;
    private BigDecimal price;

    public static PlayerResponse from(Player p) {
        return PlayerResponse.builder()
                .id(p.getId())
                .teamId(p.getTeam().getId())
                .fullName(p.getFullName())
                .number(p.getNumber())
                .position(p.getPosition())
                .age(p.getAge())
                .country(p.getCountry())
                .price(p.getPrice())
                .build();
    }
}

