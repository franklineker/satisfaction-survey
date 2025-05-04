package com.conecteVoC.conecteVoC_api.dto.survey;

import com.conecteVoC.conecteVoC_api.dto.question.UpdateQuestionDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSurveyDTO {

    private String title;
    private String description;
    private String status;
    private String surveyType;
    private Integer maxResponses;
    private boolean isAnonymous;
    private List<UpdateQuestionDTO> questions;
}
