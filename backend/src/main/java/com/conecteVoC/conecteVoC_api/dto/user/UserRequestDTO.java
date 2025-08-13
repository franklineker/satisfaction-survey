package com.conecteVoC.conecteVoC_api.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
    private Set<Integer> roles;
    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    private String taxNumber;
    private String phoneNumber;
}
