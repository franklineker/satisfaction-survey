package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.role.RoleResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserCreateDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserUpdateDTO;
import com.conecteVoC.conecteVoC_api.entity.Role;
import com.conecteVoC.conecteVoC_api.entity.User;
import com.conecteVoC.conecteVoC_api.enums.UserRole;
import com.conecteVoC.conecteVoC_api.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserMapper {

    public static User fromUserCreateDTO(UserCreateDTO dto, Set<Role> roles, PasswordEncoder passwordEncoder) {
        return dto != null ? User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .taxNumber(dto.getTaxNumber())
                .phoneNumber(dto.getPhoneNumber())
                .build() : null;
    }

    public static User fromUserUpdateDTO(UserUpdateDTO dto, Set<Role> roles) {
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
        Set<RoleResponseDTO> roles = user.getRoles().stream()
                .map(role -> {
                    int code = UserRole.ofDescription(role.getName()).getCode();
                    String description = role.getName();

                    return new RoleResponseDTO(code,description);
                })
                .collect(Collectors.toSet());

        return user != null ? UserResponseDTO.builder()
                .id(user.getId().toString())
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
