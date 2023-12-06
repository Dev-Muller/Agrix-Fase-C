package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entity.Fertilizers;

/**
 * FertilizersDto class.
 */
public record FertilizersDto(
    Long id,
    String name,
    String brand,
    String composition) {
  public Fertilizers toEntity() {
    return new Fertilizers(id, name, brand, composition, null);
  }

  public static FertilizersDto toDto(Fertilizers fertilizers) {
    return new FertilizersDto(fertilizers.getId(), fertilizers.getName(), fertilizers.getBrand(),
        fertilizers.getComposition());
  }

}
