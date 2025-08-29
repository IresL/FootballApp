package com.example.Football.entity;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "players",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_player_team_number",
                columnNames = {"team_id", "number"}
        )
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "full_name", nullable = false, length = 140)
    private String fullName;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false, length = 10)
    private String position;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 80)
    private String country;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
}

