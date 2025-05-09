package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.question.UpdateQuestionDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.CreateSurveyDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.SurveyResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.UpdateSurveyDTO;
import com.conecteVoC.conecteVoC_api.entity.Question;
import com.conecteVoC.conecteVoC_api.entity.Survey;
import com.conecteVoC.conecteVoC_api.entity.User;
import com.conecteVoC.conecteVoC_api.repository.QuestionRepository;
import com.conecteVoC.conecteVoC_api.repository.UserRepository;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class SurveyMapper {

    public static Survey fromCreateSurveyDTO(CreateSurveyDTO dto, UserRepository userRepository){
        System.out.println(dto);
        User creator = userRepository.findById(UUID.fromString(dto.getCreatorId()))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getCreatorId()));

        return dto != null ? Survey.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .surveyType(dto.getSurveyType())
                .status(dto.getStatus())
                .isAnonymous(dto.isAnonymous())
                .creator(creator)
                .build() : null;
    }

    public static SurveyResponseDTO toSurveyResponseDTO(Survey survey){
        System.out.println(String.format("Survey questions::%s", survey.getQuestions()));
        return survey != null ? SurveyResponseDTO.builder()
                .id(survey.getId().toString())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .surveyType(survey.getSurveyType())
                .status(survey.getStatus())
                .isAnonymous(survey.isAnonymous())
                .creatorId(survey.getCreator().getId().toString())
                .questions(QuestionMapper.mapToQuestionResponseDTOSet(survey.getQuestions()))
                .updatedAt(survey.getUpdatedAt())
                .createdAt(survey.getCreatedAt())
                .build() : null;
    }

    public static Set<SurveyResponseDTO> mapToSurveyResponseDTOSet(Set<Survey> surveys) {
        if (surveys == null)  Collections.emptyList();

        return surveys.stream()
                .map(SurveyMapper::toSurveyResponseDTO)
                .collect(Collectors.toSet());
    }

    public static Survey fromUpdateSurveyDTO (UpdateSurveyDTO dto, Survey survey, QuestionRepository questionRepository){
        if (dto.getTitle() != null) survey.setTitle(dto.getTitle());
        if (dto.getDescription() != null) survey.setDescription(dto.getDescription());
        if (dto.getStatus() != null) survey.setStatus(dto.getStatus());
        if (dto.getSurveyType() != null) survey.setSurveyType(dto.getSurveyType());
        survey.setAnonymous(dto.isAnonymous());
        if (dto.getQuestions() != null) {
            for(UpdateQuestionDTO updateQuestionDTO : dto.getQuestions()){
                Question question = survey.getQuestions().stream()
                        .filter(q -> q.getId().equals(updateQuestionDTO.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(
                                String.format("Question with id <%s> not found.", updateQuestionDTO.getId())));

                QuestionMapper.fromUpdateQuestionDTO(updateQuestionDTO, question);
            }
        };

        return survey;
    }

}
