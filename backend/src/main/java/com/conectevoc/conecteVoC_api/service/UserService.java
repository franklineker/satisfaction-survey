package com.conecteVoC.conecteVoC_api.service;

import com.conecteVoC.conecteVoC_api.dto.user.UserRequestDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserResponseDTO;
import com.conecteVoC.conecteVoC_api.entity.Role;
import com.conecteVoC.conecteVoC_api.entity.User;
import com.conecteVoC.conecteVoC_api.enums.UserRole;
import com.conecteVoC.conecteVoC_api.mapper.UserMapper;
import com.conecteVoC.conecteVoC_api.repository.RoleRepository;
import com.conecteVoC.conecteVoC_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO create(UserRequestDTO dto) {
        Set<String> rolesDTO = dto.getRoles().stream()
                .map(code -> UserRole.ofCode(code).getDescription())
                .collect(Collectors.toSet());
        Set<Role> roles = new HashSet<>();

        for (String str : rolesDTO) {
            Role roleToAdd = roleRepository.findByName(str);
            if (roleToAdd == null) {
                throw new IllegalArgumentException("Role '" + str + "' is invalid.");
            }
            roles.add(roleToAdd);
        }

        User user = UserMapper.fromUserCreateDTO(dto, roles, passwordEncoder);

        UserResponseDTO responseDTO = UserMapper.toUserResponseDTO(userRepository.save(user));

        return responseDTO;
    }
}
