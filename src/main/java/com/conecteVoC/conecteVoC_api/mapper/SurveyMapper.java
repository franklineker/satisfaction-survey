package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.survey.SurveyCreateDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.SurveyResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.SurveyUpdateDTO;
import com.conecteVoC.conecteVoC_api.entity.Survey;

public class SurveyMapper {

    public static Survey fromSurveyCreateDTO(SurveyCreateDTO dto){
        return dto != null ? Survey.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .surveyType(dto.getSurveyType())
                .status(dto.getStatus())
                .isAnonymous(dto.isAnonymous())
                .maxResponses(dto.getMaxResponses())
                .creatorId(dto.getCreatorId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .sentAt(dto.getSentAt())
                .respondedAt(dto.getRespondedAt())
                .build() : null;
    }

    public static Survey fromSurveyUpdateDTO(SurveyUpdateDTO dto){
        return dto != null ? Survey.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .surveyType(dto.getSurveyType())
                .status(dto.getStatus())
                .isAnonymous(dto.isAnonymous())
                .maxResponses(dto.getMaxResponses())
                .creatorId(dto.getCreatorId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .sentAt(dto.getSentAt())
                .respondedAt(dto.getRespondedAt())
                .build() : null;
    }

    public static SurveyResponseDTO toSurveyResponseDTO(Survey survey){
        return survey != null ? SurveyResponseDTO.builder()
                .id(survey.getId())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .surveyType(survey.getSurveyType())
                .status(survey.getStatus())
                .isAnonymous(survey.isAnonymous())
                .maxResponses(survey.getMaxResponses())
                .creatorId(survey.getCreatorId())
                .startDate(survey.getStartDate())
                .endDate(survey.getEndDate())
                .sentAt(survey.getSentAt())
                .respondedAt(survey.getRespondedAt())
                .updatedAt(survey.getUpdatedAt())
                .createdAt(survey.getCreatedAt())
                .build() : null;
    }
}
