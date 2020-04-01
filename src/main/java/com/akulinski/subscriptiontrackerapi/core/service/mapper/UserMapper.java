package com.akulinski.subscriptiontrackerapi.core.service.mapper;

import com.akulinski.subscriptiontrackerapi.core.domain.User;
import com.akulinski.subscriptiontrackerapi.core.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

  UserDTO asDTO(User user);

  User asDO(UserDTO userDTO);
}
