package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.CropWithFertilizerDto;
import com.betrybe.agrix.controller.dto.FertilizersDto;
import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Fertilizers;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CropController class.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  @Autowired
  private final CropService cropService;

  /**
   * CropController constructor.
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Get all crops.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<CropDto> dtoCrop = cropService.getAllCrops().stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(),
            crop.getPlantedArea(), crop.getFarm().getId(),
            crop.getPlantedDate(), crop.getHarvestDate()))
        .toList();
    return new ResponseEntity<>(dtoCrop, HttpStatus.OK);
  }

  /**
   * Get a crop by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    return new ResponseEntity<>(CropDto.toDto(cropService.getCropById(id)), HttpStatus.OK);
  }

  /**
   * Get all crops between two dates.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> findAllByHarvestDateBetween(LocalDate start, LocalDate end) {
    List<Crop> crop = cropService.findAllByHarvestDateBetween(start, end);
    List<CropDto> dtoCrop = crop.stream()
        .map(newCrop -> new CropDto(newCrop.getId(), newCrop.getName(),
            newCrop.getPlantedArea(), newCrop.getFarm().getId(),
            newCrop.getPlantedDate(), newCrop.getHarvestDate()))
        .toList();
    return new ResponseEntity<>(dtoCrop, HttpStatus.OK);
  }

  /**
   * Create a cropWithFertilizers.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> insertFertilizerToCrop(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    CropWithFertilizerDto.toDto(cropService
        .insertFertilizerToCrop(cropId, fertilizerId));
    return new ResponseEntity<>("Fertilizante e plantação associados com sucesso!",
        HttpStatus.CREATED);
  }

  /**
   * Get a crop with fertilizer by id.
   */
  @GetMapping("/{id}/fertilizers")
  public ResponseEntity<List<FertilizersDto>> getCropWithFertilizerById(@PathVariable Long id) {
    List<Fertilizers> fertilizers = cropService.getCropByIdWithFertilizers(id);

    List<FertilizersDto> fertilizersDto = fertilizers.stream()
        .map(fertilizer -> new FertilizersDto(fertilizer.getId(), fertilizer.getName(),
            fertilizer.getBrand(), fertilizer.getComposition()))
        .toList();
    
    return new ResponseEntity<>(fertilizersDto, HttpStatus.OK);
  }
}
