package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.AuthDto;
import com.betrybe.agrix.controller.dto.TokenDto;
import com.betrybe.agrix.models.entity.Person;
import com.betrybe.agrix.service.PersonService;
import com.betrybe.agrix.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller".
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private final AuthenticationManager authenticationManager;

  @Autowired
  private final TokenService tokenService;

  /**
   * Constructor.
   */
  public AuthController(AuthenticationManager authenticationManager,
      TokenService tokenService, PersonService personService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * login.
   */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody AuthDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authenticationDto.username(),
            authenticationDto.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    TokenDto tokenDto = new TokenDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
  }
}