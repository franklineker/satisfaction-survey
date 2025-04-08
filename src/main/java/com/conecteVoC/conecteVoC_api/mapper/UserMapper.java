package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.user.UserCreateDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserUpdateDTO;
import com.conecteVoC.conecteVoC_api.entity.User;
import com.conecteVoC.conecteVoC_api.enums.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static User fromUserCreateDTO(UserCreateDTO dto) {
        Set<String> roles = dto.getRoles()
                .stream()
                .map(code -> UserRole.ofCode(code).getDescription())
                .collect(Collectors.toSet());

        return dto != null ? User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(roles)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .taxNumber(dto.getTaxNumber())
                .phoneNumber(dto.getPhoneNumber())
                .build() : null;
    }

    public static User fromUserUpdateDTO(UserUpdateDTO dto) {
        Set<String> roles = dto.getRoles()
                .stream()
                .map(code -> UserRole.ofCode(code).getDescription())
                .collect(Collectors.toSet());

        return dto != null ? User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(roles)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .taxNumber(dto.getTaxNumber())
                .phoneNumber(dto.getPhoneNumber())
                .build() : null;
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        Set<String> roles = user.getRoles()
                .stream()
                .map(description -> UserRole.ofDescription(description).getDescription())
                .collect(Collectors.toSet());

        return user != null ? UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(roles)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .taxNumber(user.getTaxNumber())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .build() : null;
    }
}
