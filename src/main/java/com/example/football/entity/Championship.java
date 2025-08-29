package com.example.football.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "championships")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "championship")
    private List<Team> teams = new ArrayList<>();
}
