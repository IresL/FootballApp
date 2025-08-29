package com.example.football.repository;



import com.example.football.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUsername(String username);
    boolean existsByUsernameAndPlayer_Id(String username, Long playerId);
    void deleteByUsernameAndPlayer_Id(String username, Long playerId);
}

