package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entity.Fertilizers;
import com.betrybe.agrix.models.repository.FertilizersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FertilizersService class.
 */
@Service
public class FertilizersService {

  @Autowired
  FertilizersRepository fertilizersRepository;

  /**
   * FertilizersService constructor.
   */
  public FertilizersService(FertilizersRepository fertilizersRepository) {
    this.fertilizersRepository = fertilizersRepository;
  }

  /**
   * Create a fertilizer.
   */
  public Fertilizers createFertilizers(Fertilizers fertilizers) {
    return fertilizersRepository.save(fertilizers);
  }

  /**
   * Get all fertilizer.
   */
  public List<Fertilizers> getAllFertilizers() {
    return fertilizersRepository.findAll();
  }

  /**
   * Get a fertilizer by id.
   */
  public Fertilizers getFertilizersById(Long id) {
    Optional<Fertilizers> fertilizers = fertilizersRepository.findById(id);
    if (fertilizers.isEmpty()) {
      throw new FertilizerNotFoundException();
    }
    return fertilizersRepository.findById(id).get();
  }
}
