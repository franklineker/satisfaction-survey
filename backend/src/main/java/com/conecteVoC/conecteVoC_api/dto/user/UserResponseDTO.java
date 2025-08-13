package com.conecteVoC.conecteVoC_api.dto.user;

import com.conecteVoC.conecteVoC_api.dto.role.RoleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String id;
    private String username;
    private Set<RoleResponseDTO> roles;
    private String firstName;
    private String lastName;
    private String taxNumber;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
