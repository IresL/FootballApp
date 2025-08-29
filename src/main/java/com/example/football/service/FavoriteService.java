package com.example.football.service;



import com.example.football.entity.Favorite;
import com.example.football.entity.Player;
import com.example.football.repository.FavoriteRepository;
import com.example.football.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final PlayerRepository playerRepository;

    @Transactional
    public Favorite add(String username, Long playerId) {
        if (favoriteRepository.existsByUsernameAndPlayer_Id(username, playerId)) {
            // უკვე ფავორიტშია — არაფერს ვშლით/ვამატებთ, აბრუნებთ არსებულს
            return favoriteRepository.findByUsername(username).stream()
                    .filter(f -> f.getPlayer().getId().equals(playerId))
                    .findFirst()
                    .orElseThrow();
        }
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found: " + playerId));
        Favorite f = Favorite.builder().username(username).player(player).build();
        return favoriteRepository.save(f);
    }

    @Transactional(readOnly = true)
    public List<Favorite> list(String username) {
        return favoriteRepository.findByUsername(username);
    }

    @Transactional
    public void remove(String username, Long playerId) {
        if (!favoriteRepository.existsByUsernameAndPlayer_Id(username, playerId)) {
            throw new EntityNotFoundException("Favorite not found for player: " + playerId);
        }
        favoriteRepository.deleteByUsernameAndPlayer_Id(username, playerId);
    }
}

