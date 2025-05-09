package com.conecteVoC.conecteVoC_api.dto.survey;

import com.conecteVoC.conecteVoC_api.dto.question.UpdateQuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    private Set<UpdateQuestionDTO> questions;
}
