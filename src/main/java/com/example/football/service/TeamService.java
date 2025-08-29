package com.example.football.service;



import com.example.football.entity.Championship;
import com.example.football.entity.Team;
import com.example.football.repository.ChampionshipRepository;
import com.example.football.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ChampionshipRepository championshipRepository;

    @Transactional
    public Team create(Long championshipId, String name, int titlesCount) {
        Championship champ = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new EntityNotFoundException("Championship not found: " + championshipId));

        Team team = Team.builder()
                .championship(champ)
                .name(name)
                .titlesCount(titlesCount)
                .build();

        return teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public List<Team> findByChampionshipId(Long championshipId) {
        return teamRepository.findByChampionshipId(championshipId);
    }
}

