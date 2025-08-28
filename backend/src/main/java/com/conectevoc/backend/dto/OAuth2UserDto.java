package com.conectevoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2UserDto {

    private String clientId;
    private String clientSecret;
    private String authorizationGrantTypes;
    private String authenticationMethods;
    private Set<String> redirectUris;
    private Set<String> scopes;
}
