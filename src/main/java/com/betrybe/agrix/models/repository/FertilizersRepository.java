package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entity.Fertilizers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FertilizersRepository interface.
 */
public interface FertilizersRepository extends JpaRepository<Fertilizers, Long> {
    
}
