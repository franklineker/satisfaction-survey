package com.conecteVoC.conecteVoC_api.dto.survey;

import com.conecteVoC.conecteVoC_api.dto.question.CreateQuestionDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSurveyDTO {

    @NotNull
    private String title;
    @NotNull
    private String description;
    private String status;
    private String surveyType;
    private int maxResponses;
    private boolean isAnonymous;
    private String creatorId;

    private List<CreateQuestionDTO> questions;
}
