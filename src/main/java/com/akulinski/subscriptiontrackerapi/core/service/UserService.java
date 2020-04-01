package com.akulinski.subscriptiontrackerapi.core.service;

import com.akulinski.subscriptiontrackerapi.core.repository.UserRepository;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import com.akulinski.subscriptiontrackerapi.core.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  public UserDTO create(UserDTO userDTO) {
    final var user = userMapper.asDO(userDTO);

    final var save = userRepository.save(user);

    return userMapper.asDTO(save);
  }
}
