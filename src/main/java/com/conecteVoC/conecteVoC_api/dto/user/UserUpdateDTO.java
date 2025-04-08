package com.conecteVoC.conecteVoC_api.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private String username;
    private String password;
    private Set<Integer> roles;
    private String firstName;
    private String lastName;
    private String taxNumber;
    private String phoneNumber;
}
