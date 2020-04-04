package com.akulinski.subscriptiontrackerapi.utils;

import com.akulinski.subscriptiontrackerapi.core.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
@Slf4j
public class SecurityWrapper implements Serializable {

  private static final long serialVersionUID = -2550185165626007488L;

  private static final long JWT_TOKEN_VALIDITY =
      5 * 60 * 60; // TODO implement refresh token insted of long lived token

  private final String secret;

  public SecurityWrapper(@Value("${jwt.secret}") String secret) {
    this.secret = secret;
  }

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public User getUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  public Optional<User> getOptionalUser() {
    User principal = null;
    try {
      principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (NullPointerException e) {
      log.warn("No user in context");
    }

    return Optional.ofNullable(principal);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.getUsername());
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
