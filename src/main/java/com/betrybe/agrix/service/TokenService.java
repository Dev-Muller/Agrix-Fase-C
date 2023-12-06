package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.models.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Token Service.
 */
@Service
public class TokenService {

  @Value("secret")
  private String secret;

  /**
   * Generate Token.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withIssuer("agrixdb")
        .withSubject(person.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * Validate Token.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
        .withIssuer("agrixdb")
        .build()
        .verify(token)
        .getSubject();
  }

  /**
   * Generate Expiration Date for token.
   */
  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }
}