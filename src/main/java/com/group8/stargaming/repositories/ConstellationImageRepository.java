package com.group8.stargaming.repositories;

import com.group8.stargaming.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstellationImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByname(String name);
}
