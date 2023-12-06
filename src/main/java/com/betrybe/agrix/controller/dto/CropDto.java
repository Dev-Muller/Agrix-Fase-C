package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entity.Crop;
import java.time.LocalDate;

/**
 * CropDto class.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    Long farmId,
    LocalDate plantedDate,
    LocalDate harvestDate) {
  // List<Fertilizers> fertilizers
  public CropDto toEntity() {
    return new CropDto(id, name, plantedArea, farmId, plantedDate, harvestDate);
  }

  /**
   * CropDto constructor.
   */
  public static CropDto toDto(Crop crop) {
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
        crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate());
  }
}
