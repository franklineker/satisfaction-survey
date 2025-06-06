package com.conecteVoC.conecteVoC_api.dto.contact;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactDTO {

    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    private String phoneNumber;
    private String taxNumber;
}
