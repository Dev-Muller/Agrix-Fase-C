package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Person Repository.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  UserDetails findByUsername(String username);
}