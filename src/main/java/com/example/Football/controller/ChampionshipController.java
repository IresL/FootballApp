package com.example.Football.controller;



import com.example.Football.dto.ChampionshipCreateRequest;
import com.example.Football.dto.ChampionshipResponse;
import com.example.Football.entity.Championship;
import com.example.Football.service.ChampionshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/championships")
@RequiredArgsConstructor
public class ChampionshipController {

    private final ChampionshipService championshipService;

    @PostMapping
    public ResponseEntity<ChampionshipResponse> create(@RequestBody ChampionshipCreateRequest req) {
        Championship c = championshipService.create(req.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChampionshipResponse.from(c));
    }

    @GetMapping
    public List<ChampionshipResponse> findAll() {
        return championshipService.findAll().stream().map(ChampionshipResponse::from).toList();
    }
}

