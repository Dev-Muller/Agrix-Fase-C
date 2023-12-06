package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * NewPersonDto class.
 */
public record NewPersonDto(Long id, String username, String password, Role role) {

  public Person toDto() {
    return new Person(id, username, password, role);
  }
}