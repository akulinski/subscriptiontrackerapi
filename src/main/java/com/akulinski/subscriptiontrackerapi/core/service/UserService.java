package com.akulinski.subscriptiontrackerapi.core.service;

import com.akulinski.subscriptiontrackerapi.core.domain.Authority;
import com.akulinski.subscriptiontrackerapi.core.domain.AuthorityType;
import com.akulinski.subscriptiontrackerapi.core.repository.AuthorityRepository;
import com.akulinski.subscriptiontrackerapi.core.repository.UserRepository;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import com.akulinski.subscriptiontrackerapi.core.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  private final AuthorityRepository authorityRepository;

  public UserDTO create(UserDTO userDTO) {
    final var user = userMapper.asDO(userDTO);
    user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
    final var authority = new Authority();
    authority.setAuthorityType(AuthorityType.USER);
    authorityRepository.save(authority);

    user.setAuthorities(Set.of(authority));

    final var save = userRepository.save(user);

    return userMapper.asDTO(save);
  }
}
