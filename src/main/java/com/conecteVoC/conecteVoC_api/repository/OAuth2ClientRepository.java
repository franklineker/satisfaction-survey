package com.conecteVoC.conecteVoC_api.repository;

import com.conecteVoC.conecteVoC_api.entity.OAuth2Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuth2ClientRepository extends JpaRepository<OAuth2Client, Integer> {
    Optional<OAuth2Client> findByClientId(String clientId);
}