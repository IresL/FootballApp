package com.example.football.repository;



import com.example.football.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByChampionshipId(Long championshipId);
}

