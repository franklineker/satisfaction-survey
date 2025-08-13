package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.oauth2.OAuth2ClientRequest;
import com.conecteVoC.conecteVoC_api.entity.OAuth2Client;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Date;
import java.util.stream.Collectors;

@NoArgsConstructor
public class OAuth2ClientMapper {

    public static OAuth2Client toOAuth2Client(OAuth2ClientRequest request) {

        return request != null ? OAuth2Client.builder()
                .clientId(request.getClientId())
                .clientSecret(request.getClientSecret())
                .clientIdIssuedAt(new Date().toInstant())
                .authenticationMethods(request.getAuthenticationMethods())
                .authorizationGrantTypes(request.getAuthorizationGrantTypes())
                .redirectUris(request.getRedirectUris())
                .scopes(request.getScopes())
                .requireProofKey(request.getRequireProofKey())
                .build() : null;
    }

    public static RegisteredClient toRegisteredClient(OAuth2Client client) {
        RegisteredClient.Builder builder = RegisteredClient
                .withId(client.getClientId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(am -> am.addAll(client.getAuthenticationMethods()
                        .stream()
                        .map(method -> new ClientAuthenticationMethod(method))
                        .collect(Collectors.toSet())))
                .authorizationGrantTypes(agt -> agt.addAll(client.getAuthorizationGrantTypes()
                        .stream()
                        .map(gt -> new AuthorizationGrantType(gt))
                        .collect(Collectors.toSet())))
                .redirectUris(ru -> ru.addAll(client.getRedirectUris()))
                .scopes(sc -> sc.addAll(client.getScopes()))
                .clientSettings(ClientSettings
                        .builder()
                        .requireProofKey(client.getRequireProofKey())
                        .requireAuthorizationConsent(true)
                        .build()
                );
        return builder.build();
    }
}
