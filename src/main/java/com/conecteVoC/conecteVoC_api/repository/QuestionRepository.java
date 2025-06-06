package com.conecteVoC.conecteVoC_api.repository;

import com.conecteVoC.conecteVoC_api.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
