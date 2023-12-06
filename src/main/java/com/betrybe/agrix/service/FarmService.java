package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.models.entity.Farm;
import com.betrybe.agrix.models.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * FarmService class.
 */
@Service
public class FarmService {

  @Autowired
  FarmRepository farmRepository;

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Get a farm by id.
   */
  public Farm getFarmById(Long id) {
    Optional<Farm> farm = farmRepository.findById(id);
    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    } 
    return farm.get();
  }
}
