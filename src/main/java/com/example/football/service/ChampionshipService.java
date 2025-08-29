package com.example.football.service;



import com.example.football.entity.Championship;
import com.example.football.repository.ChampionshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    @Transactional
    public Championship create(String name) {
        Championship c = Championship.builder().name(name).build();
        return championshipRepository.save(c);
    }

    @Transactional(readOnly = true)
    public List<Championship> findAll() {
        return championshipRepository.findAll();
    }
}

