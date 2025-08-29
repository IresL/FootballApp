package com.example.football.controller;



import com.example.football.dto.TeamCreateRequest;
import com.example.football.dto.TeamResponse;
import com.example.football.entity.Team;
import com.example.football.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/championships/{championshipId}/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponse> create(
            @PathVariable Long championshipId,
            @RequestBody TeamCreateRequest req
    ) {
        Team t = teamService.create(championshipId, req.getName(), req.getTitlesCount());
        return ResponseEntity.status(HttpStatus.CREATED).body(TeamResponse.from(t));
    }

    @GetMapping
    public List<TeamResponse> list(@PathVariable Long championshipId) {
        return teamService.findByChampionshipId(championshipId).stream()
                .map(TeamResponse::from)
                .toList();
    }
}

