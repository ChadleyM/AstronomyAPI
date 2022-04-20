package com.group8.stargaming.repositories;

import com.group8.stargaming.models.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group8.stargaming.models.StarDetails;

import java.util.Optional;

public interface StarAlmanacRepository extends JpaRepository<StarDetails, Long>{

}
