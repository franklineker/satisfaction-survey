package com.conecteVoC.conecteVoC_api.repository;

import com.conecteVoC.conecteVoC_api.entity.ActiveContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActiveContactRepository extends JpaRepository<ActiveContact, UUID> {
    Page<ActiveContact> findAll(Pageable pageable);
}
