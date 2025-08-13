package com.conecteVoC.conecteVoC_api.service;

import com.conecteVoC.conecteVoC_api.dto.oauth2.OAuth2ClientRequest;
import com.conecteVoC.conecteVoC_api.entity.OAuth2Client;
import com.conecteVoC.conecteVoC_api.mapper.OAuth2ClientMapper;
import com.conecteVoC.conecteVoC_api.repository.OAuth2ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OAuth2ClientService implements RegisteredClientRepository {
    private OAuth2ClientRepository oAuth2ClientRepository;
    private final PasswordEncoder passwordEncoder;

    public OAuth2Client saveClient(OAuth2ClientRequest request) {
        OAuth2Client oAuth2Client = OAuth2ClientMapper.toOAuth2Client(request);
        oAuth2Client.setClientSecret(passwordEncoder.encode(request.getClientSecret()));

        return oAuth2ClientRepository.save(oAuth2Client);
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        OAuth2Client client = oAuth2ClientRepository.findByClientId(id).orElseThrow(() -> new RuntimeException("Client not found."));
        System.out.println(client);
        return OAuth2ClientMapper.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {

        Optional<OAuth2Client> clientOpt;
        try {
            clientOpt = oAuth2ClientRepository.findByClientId(clientId);
        } catch (Exception e) {
            throw e;
        }

        OAuth2Client oAuth2Client = clientOpt.orElseThrow(() -> new RuntimeException("Client Not Found"));
        return OAuth2ClientMapper.toRegisteredClient(oAuth2Client);
    }
}
