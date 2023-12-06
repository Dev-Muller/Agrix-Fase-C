package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.NewPersonDto;
import com.betrybe.agrix.controller.dto.PersonsDto;
import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.models.entity.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Persons Controller.
 */
@Controller
@RequestMapping("/persons")
public class PersonsController {

  @Autowired
  private final PersonService personService;

  public PersonsController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Creates a new person.
   */
  @PostMapping()
  public ResponseEntity<PersonsDto> createPerson(@RequestBody NewPersonDto newPersonDto) {
    UserDetails userDetails = personService.loadUserByUsername(newPersonDto.username());
    if (userDetails != null) {
      throw new PersonNotFoundException();
    }
    Person person = personService.createPerson(newPersonDto.toDto());
    PersonsDto personDto = new PersonsDto(person.getId(), person.getUsername(), person.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
  }
}