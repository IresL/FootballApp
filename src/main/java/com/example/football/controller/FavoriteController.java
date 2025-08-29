package com.example.football.controller;



import com.example.football.dto.PlayerResponse;
import com.example.football.entity.Favorite;
import com.example.football.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/players/{playerId}")
    public ResponseEntity<PlayerResponse> add(@PathVariable Long playerId) {
        String username = currentUser();
        Favorite f = favoriteService.add(username, playerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PlayerResponse.from(f.getPlayer()));
    }

    @GetMapping("/players")
    public List<PlayerResponse> list() {
        String username = currentUser();
        return favoriteService.list(username).stream()
                .map(Favorite::getPlayer)
                .map(PlayerResponse::from)
                .toList();
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> remove(@PathVariable Long playerId) {
        String username = currentUser();
        favoriteService.remove(username, playerId);
        return ResponseEntity.noContent().build();
    }

    private String currentUser() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return a.getName();
    }
}

