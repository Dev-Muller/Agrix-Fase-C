package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FarmRepository interface.
 */
public interface FarmRepository extends JpaRepository<Farm, Long> {
    
}
