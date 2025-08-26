package com.conecteVoC.conecteVoC_api.dto.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OAuth2ClientRequest {
    private String clientId;
    private String clientSecret;
    private Set<String> authorizationGrantTypes;
    private Set<String> authenticationMethods;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private Boolean requireProofKey;
}
