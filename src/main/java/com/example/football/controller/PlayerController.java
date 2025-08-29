package com.example.football.controller;



import com.example.football.dto.PlayerCreateRequest;
import com.example.football.dto.PlayerResponse;
import com.example.football.entity.Player;
import com.example.football.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/teams/{teamId}/players")
    public ResponseEntity<PlayerResponse> create(
            @PathVariable Long teamId,
            @RequestBody PlayerCreateRequest req
    ) {
        Player p = playerService.create(
                teamId,
                req.getFullName(),
                req.getNumber(),
                req.getPosition(),
                req.getAge(),
                req.getCountry(),
                req.getPrice()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(PlayerResponse.from(p));
    }

    @GetMapping("/teams/{teamId}/players")
    public List<PlayerResponse> list(@PathVariable Long teamId) {
        return playerService.findByTeamId(teamId).stream()
                .map(PlayerResponse::from)
                .toList();
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> delete(@PathVariable Long playerId) {
        playerService.delete(playerId);
        return ResponseEntity.noContent().build();
    }
}

