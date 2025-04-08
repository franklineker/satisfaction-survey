package com.conecteVoC.conecteVoC_api.service;

import com.conecteVoC.conecteVoC_api.dto.user.UserCreateDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserResponseDTO;
import com.conecteVoC.conecteVoC_api.entity.User;
import com.conecteVoC.conecteVoC_api.mapper.UserMapper;
import com.conecteVoC.conecteVoC_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO create(UserCreateDTO dto) {
        User user = UserMapper.fromUserCreateDTO(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        UserResponseDTO responseDTO = UserMapper.toUserResponseDTO(userRepository.save(user));

        return responseDTO;
    }
}
