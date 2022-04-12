package com.group8.stargaming.repositories;

import com.group8.stargaming.models.StarDetailsID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group8.stargaming.models.StarDetails;

public interface StarAlmanacRepository extends JpaRepository<StarDetails, StarDetailsID>{

}
