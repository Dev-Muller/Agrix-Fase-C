package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.NewPersonDto;
import com.betrybe.agrix.controller.dto.PersonsDto;
import com.betrybe.agrix.models.entity.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PersonsController class.
 */
@Controller
@RequestMapping("persons")
public class PersonsController {

  @Autowired
  private final PersonService personService;

  public PersonsController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create a new person.
   */
  @PostMapping()
  public ResponseEntity<PersonsDto> createPerson(@RequestBody NewPersonDto newPersonDto) {
    Person person = personService.create(newPersonDto.toDto());
    PersonsDto personsDto = new PersonsDto(person.getId(), person.getUsername(), person.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(personsDto);
  }
}