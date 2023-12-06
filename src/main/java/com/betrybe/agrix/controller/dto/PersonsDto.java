package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.security.Role;

/**
 * PersonsDto class.
 */
public record PersonsDto(Long id, String username, Role role) {

}