package com.group8.stargaming.repositories;

import com.group8.stargaming.models.Constellation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    
}
