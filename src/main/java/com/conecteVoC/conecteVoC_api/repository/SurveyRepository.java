package com.conecteVoC.conecteVoC_api.repository;

import com.conecteVoC.conecteVoC_api.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, String> {
}
