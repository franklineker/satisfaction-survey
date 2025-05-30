package com.conecteVoC.conecteVoC_api.repository;

import com.conecteVoC.conecteVoC_api.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    public Role findByName(String name);
}
