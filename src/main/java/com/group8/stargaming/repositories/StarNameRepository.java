package com.group8.stargaming.repositories;

import com.group8.stargaming.models.Constellation;
import com.group8.stargaming.models.StarName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StarNameRepository extends JpaRepository<StarName, Long>{

}
