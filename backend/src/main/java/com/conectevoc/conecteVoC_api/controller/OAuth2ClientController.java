package com.conecteVoC.conecteVoC_api.controller;

import com.conecteVoC.conecteVoC_api.dto.oauth2.OAuth2ClientRequest;
import com.conecteVoC.conecteVoC_api.entity.OAuth2Client;
import com.conecteVoC.conecteVoC_api.service.OAuth2ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/client")
public class OAuth2ClientController {

    @Autowired
    private OAuth2ClientService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OAuth2Client> createOAuth2Client(@RequestBody OAuth2ClientRequest request) {
        OAuth2Client oAuth2Client = service.saveClient(request);
        return ResponseEntity.ok(oAuth2Client);
    }
}
