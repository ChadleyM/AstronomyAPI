package com.group8.stargaming.repositories;

import java.util.List;
import java.util.Optional;

import com.group8.stargaming.models.Constellation;

import com.group8.stargaming.models.ConstellationEdge;
import com.group8.stargaming.models.StarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    
    Optional<Constellation> findByName(String name);
//    Optional<List<Constellation>> findAllByStars(List<StarDetails> starlist);

//    Optional<Constellation> findByEdgeList(List<ConstellationEdge> edgeList);
}
