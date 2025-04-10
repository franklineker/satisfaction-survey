package com.conecteVoC.conecteVoC_api.controller;

import com.conecteVoC.conecteVoC_api.dto.user.UserCreateDTO;
import com.conecteVoC.conecteVoC_api.dto.user.UserResponseDTO;
import com.conecteVoC.conecteVoC_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateDTO dto){
        UserResponseDTO responseDTO = userService.create(dto);
        return ResponseEntity.ok(responseDTO);
    }
}
