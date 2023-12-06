package com.betrybe.agrix.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 * Fertilizers entity.
 */
@Entity
@Table(name = "fertilizers")
@Data
public class Fertilizers {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String brand;
  private String composition;

  @ManyToMany
  @JoinColumn(name = "crop_id")
  private List<Crop> crop;

  /**
   * Constructor vazio.
   */
  public Fertilizers() {
  }

  /**
   * Constructor.
   */
  public Fertilizers(Long id, String name, String brand, String composition, List<Crop> crop) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
    this.crop = crop;
  }
}
