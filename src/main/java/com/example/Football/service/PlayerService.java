package com.example.Football.service;



import com.example.Football.entity.Player;
import com.example.Football.entity.Team;
import com.example.Football.repository.PlayerRepository;
import com.example.Football.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Player create(Long teamId, String fullName, int number, String position, int age, String country, BigDecimal price) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found: " + teamId));

        Player player = Player.builder()
                .team(team)
                .fullName(fullName)
                .number(number)
                .position(position)
                .age(age)
                .country(country)
                .price(price)
                .build();

        return playerRepository.save(player);
    }

    @Transactional
    public void delete(Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new EntityNotFoundException("Player not found: " + playerId);
        }
        playerRepository.deleteById(playerId);
    }

    @Transactional(readOnly = true)
    public List<Player> findByTeamId(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }
}

