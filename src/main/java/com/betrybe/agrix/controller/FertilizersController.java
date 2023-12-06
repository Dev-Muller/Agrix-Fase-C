package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizersDto;
import com.betrybe.agrix.models.entity.Fertilizers;
import com.betrybe.agrix.service.FertilizersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FertilizersController class.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizersController {

  @Autowired
  private final FertilizersService fertilizersService;

  /**
   * FertilizersController constructor.
   */
  public FertilizersController(FertilizersService fertilizersService) {
    this.fertilizersService = fertilizersService;
  }

  /**
   * Create a fertilizer.
   */
  @PostMapping
  public ResponseEntity<FertilizersDto> createFertilizers(
      @RequestBody FertilizersDto fertilizersDto) {
    Fertilizers newFertilizers = fertilizersService.createFertilizers(fertilizersDto.toEntity());
    FertilizersDto newFertilizersDto = FertilizersDto.toDto(newFertilizers);
    return new ResponseEntity<>(newFertilizersDto, HttpStatus.CREATED);
  }

  /**
   * Get all fertilizers.
   */
  @GetMapping
  public ResponseEntity<List<FertilizersDto>> getAllFertilizers() {
    List<FertilizersDto> fertilizers = fertilizersService.getAllFertilizers().stream()
        .map(fertilizer -> new FertilizersDto(fertilizer.getId(), fertilizer.getName(),
            fertilizer.getBrand(), fertilizer.getComposition()))
        .toList();
    return new ResponseEntity<>(fertilizers, HttpStatus.OK);
  }

  /**
   * Get a fertilizer by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizersDto> getFertilizersById(@PathVariable Long id) {
    Fertilizers fertilizers = fertilizersService.getFertilizersById(id);
    FertilizersDto fertilizersDto = FertilizersDto.toDto(fertilizers);
    return new ResponseEntity<>(fertilizersDto, HttpStatus.OK);
  }
}
