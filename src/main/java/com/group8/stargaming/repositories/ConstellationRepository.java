package com.group8.stargaming.repositories;

import java.util.List;
import java.util.Optional;

import com.group8.stargaming.models.Constellation;

import com.group8.stargaming.models.ConstellationEdge;
import com.group8.stargaming.models.StarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    
    Optional<Constellation> findByName(String name);
}
