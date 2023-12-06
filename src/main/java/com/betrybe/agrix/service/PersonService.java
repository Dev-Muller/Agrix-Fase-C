package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.models.entity.Person;
import com.betrybe.agrix.models.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Person Service.
 */
@Service
public class PersonService implements UserDetailsService {

  @Autowired
  private final PersonRepository personRepository;

  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Get a person for a given ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Creates a new person.
   */
  public Person createPerson(Person person) {

    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);

    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username);
  }
}