package com.conecteVoC.conecteVoC_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "oauth2_client")
@Entity
public class OAuth2Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, name = "client_id", unique = true)
    private String clientId;

    @Column(name = "client_id_issued_at")
    private Instant clientIdIssuedAt;
    @Column(name = "client_secret",nullable = false)
    private String clientSecret;
    @Column(name = "authorization_grand_types")
    private Set<String> authorizationGrantTypes;
    @Column(name = "authentication_methods")
    private Set<String> authenticationMethods;
    @Column(name = "redirect_uris")
    private Set<String> redirectUris;
    @Column(name = "scopes")
    private Set<String> scopes;
    @Column(name = "required_proof_key")
    private Boolean requireProofKey;
}
