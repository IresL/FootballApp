package com.example.football.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "teams",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_team_championship_name",
                columnNames = {"championship_id", "name"}
        )
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "championship_id", nullable = false)
    private Championship championship;

    @Column(nullable = false, length = 120)
    private String name;

    @Builder.Default
    @Column(name = "titles_count", nullable = false)
    private int titlesCount = 0;

    @Builder.Default
    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();
}

