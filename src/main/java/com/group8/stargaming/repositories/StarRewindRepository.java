package com.group8.stargaming.repositories;

import com.group8.stargaming.models.StarRewind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StarRewindRepository extends JpaRepository<StarRewind, String>{
    @Query(value = "SELECT * FROM Star_Details WHERE Star_Details.Date = :date", nativeQuery = true)
    List<StarRewind> findAllByDate(String date);

    List<StarRewind> findAll();
}
