package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.models.entity.Crop;
import com.betrybe.agrix.models.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
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
 * FarmController class.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  @Autowired
  private final FarmService farmService;

  @Autowired
  private final CropService cropService;

  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create a farm.
   */
  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
    Farm newFarm = farmService.createFarm(farm);
    FarmDto newFarmDto = new FarmDto(newFarm.getId(), newFarm.getName(), newFarm.getSize());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarmDto.toFarm());
  }

  /**
   * Get all farms.
   */
  @GetMapping
  public ResponseEntity<List<Farm>> getAllFarms() {
    return new ResponseEntity<>(farmService.getAllFarms(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
    return new ResponseEntity<>(farmService.getFarmById(id), HttpStatus.OK);
  }

  /**
   * Create a crop.
   */
  @PostMapping("/{id}/crops")
  public ResponseEntity<?> createCrop(@PathVariable Long id, @RequestBody Crop crop) {
    Farm farm = farmService.getFarmById(id);

    crop.setFarm(farm);
    Crop savedCrop = cropService.createCrop(crop);

    CropDto dtoCrop = new CropDto(savedCrop.getId(), savedCrop.getName(),
        savedCrop.getPlantedArea(), savedCrop.getFarm().getId(),
        savedCrop.getPlantedDate(), savedCrop.getHarvestDate());

    return new ResponseEntity<>(dtoCrop, HttpStatus.CREATED);
  }

  /**
   * Get all crops from a farm.
   */
  @GetMapping("/{id}/crops")
  public ResponseEntity<?> getAllCrops(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);

    List<CropDto> dtoCrops = farm.getCrops().stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .toList();

    return new ResponseEntity<>(dtoCrops, HttpStatus.OK);
  }
}
