package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Fertilizers;
import com.betrybe.agrix.models.repository.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CropService class.
 */
@Service
public class CropService {

  @Autowired
  CropRepository cropRepository;

  @Autowired
  FertilizersService fertilzersService;

  public Crop createCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Get a crop by id.
   */
  public Crop getCropById(Long id) {
    Optional<Crop> crop = cropRepository.findById(id);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropRepository.findById(id).get();
  }

  public List<Crop> findAllByHarvestDateBetween(LocalDate start, LocalDate end) {
    return cropRepository.findAllByHarvestDateBetween(start, end);
  }

  /**
   * Insert a fertilizer to a crop.
   */
  public Crop insertFertilizerToCrop(Long cropId, Long fertilizerId) {
    Crop crop = getCropById(cropId);
    Fertilizers fertilizers = fertilzersService.getFertilizersById(fertilizerId);
    crop.getFertilizers().add(fertilizers);
    return cropRepository.save(crop);
  }

  /**
   * Get crop by id with fertilizers.
   */
  public List<Fertilizers> getCropByIdWithFertilizers(Long id) {
    Optional<Crop> crop = cropRepository.findById(id);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return crop.get().getFertilizers();
  }
}
