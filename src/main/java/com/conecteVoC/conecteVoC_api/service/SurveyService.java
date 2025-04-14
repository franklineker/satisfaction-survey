package com.conecteVoC.conecteVoC_api.service;

import com.conecteVoC.conecteVoC_api.dto.survey.SurveyCreateDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.SurveyResponseDTO;
import com.conecteVoC.conecteVoC_api.entity.Survey;
import com.conecteVoC.conecteVoC_api.mapper.SurveyMapper;
import com.conecteVoC.conecteVoC_api.repository.SurveyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyResponseDTO create(SurveyCreateDTO dto) {
        Survey survey = SurveyMapper.fromSurveyCreateDTO(dto);
        SurveyResponseDTO responseDTO = SurveyMapper.toSurveyResponseDTO(surveyRepository.save(survey));
        return responseDTO;
    }
}
