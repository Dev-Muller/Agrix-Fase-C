package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entity.Farm;

/**
 * FarmDto class.
 */
public record FarmDto(
    Long id,
    String name,
    double size
) {
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
