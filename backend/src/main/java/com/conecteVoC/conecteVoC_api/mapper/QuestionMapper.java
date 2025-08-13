package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.question.CreateQuestionDTO;
import com.conecteVoC.conecteVoC_api.dto.question.QuestionResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.question.UpdateQuestionDTO;
import com.conecteVoC.conecteVoC_api.entity.Question;
import com.conecteVoC.conecteVoC_api.entity.Survey;
import com.conecteVoC.conecteVoC_api.enums.QuestionType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static Question toQuestion(CreateQuestionDTO dto, Survey survey){
        return dto != null ? Question.builder()
                .survey(survey)
                .text(dto.getText())
                .type(QuestionType.ofCode(dto.getTypeCode()))
                .isRequired(dto.isRequired())
                .displayOrder(dto.getDisplayOrder())
                .scaleMin(dto.getScaleMin())
                .scaleMax(dto.getScaleMax())
                .build() : null;
    }

    public static Question fromUpdateQuestionDTO(UpdateQuestionDTO dto, Question question){
        if (dto.getText() != null) question.setText(dto.getText());
        if (dto.getTypeCode() != null) question.setType(QuestionType.ofCode(dto.getTypeCode()));
        if (dto.getIsRequired() != null) question.setRequired(dto.getIsRequired());
        if (dto.getDisplayOrder() != null) question.setDisplayOrder(dto.getDisplayOrder());
        if (dto.getScaleMin() != null) question.setScaleMin(dto.getScaleMin());
        if (dto.getScaleMax() != null) question.setScaleMax(dto.getScaleMax());

        return question;
    }

    public static QuestionResponseDTO toQuestionResponseDTO(Question question) {
        return question != null ? QuestionResponseDTO.builder()
                .id(question.getId().toString())
                .surveyId(question.getSurvey().getId().toString())
                .text(question.getText())
                .isRequired(question.isRequired())
                .type(question.getType())
                .typeLabel(question.getType().getDescription())
                .typeCode(question.getType().getCode())
                .scaleMin(question.getScaleMin())
                .scaleMax(question.getScaleMax())
                .displayOrder(question.getDisplayOrder())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .build() : null;
    }

    public static List<Question> toQuestionList(
            List<UpdateQuestionDTO> questionsDTO,
            Function<UUID, Question> questionFetcher) {

        if (questionsDTO == null) {
            return Collections.emptyList();
        }

        return questionsDTO.stream()
                .map(dto -> {
                    Question question = questionFetcher.apply(dto.getId());
                    return fromUpdateQuestionDTO(dto, question);
                })
                .collect(Collectors.toList());
    }

    public static Set<QuestionResponseDTO> mapToQuestionResponseDTOSet(Set<Question> questions){
        System.out.println(questions);
        if (questions == null) {
            return Collections.emptySet();
        }

        return new HashSet<>(questions).stream()
                .map(QuestionMapper::toQuestionResponseDTO)
                .collect(Collectors.toSet());
    }


}
