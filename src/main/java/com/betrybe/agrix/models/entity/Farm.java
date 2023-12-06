package com.betrybe.agrix.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

/**
 * Farm entity.
 */
@Entity
@Data
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double size;
  @OneToMany(mappedBy = "farm")
  @JsonIgnore
  private List<Crop> crops;

  public Farm() {
  }

  /**
   * Farm constructor.
   */
  public Farm(Long id, String name, double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }
}
