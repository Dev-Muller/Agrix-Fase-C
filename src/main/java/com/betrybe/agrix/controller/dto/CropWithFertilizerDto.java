package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Fertilizers;
import java.time.LocalDate;
import java.util.List;

/**
 * CropWithFertilizersDto class.
 */
public record CropWithFertilizerDto(
    Long id,
    String name,
    Double plantedArea,
    Long farmId,
    LocalDate plantedDate,
    LocalDate harvestDate,
    List<Fertilizers> fertilizers) {
  public CropWithFertilizerDto toEntity() {
    return new CropWithFertilizerDto(id, name, plantedArea, farmId, plantedDate,
        harvestDate, fertilizers);
  }

  /**
   * CropDto constructor.
   */
  public static CropWithFertilizerDto toDto(Crop crop) {
    return new CropWithFertilizerDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
        crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate(),
        crop.getFertilizers());
  }
}
