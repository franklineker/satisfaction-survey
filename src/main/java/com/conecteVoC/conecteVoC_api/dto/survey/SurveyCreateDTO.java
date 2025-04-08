package com.conecteVoC.conecteVoC_api.dto.survey;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyCreateDTO {

    @NotNull
    private String title;
    @NotNull
    private String description;
    private String status;
    private String surveyType;
    private int maxResponses;
    private boolean isAnonymous;
    private String sentAt;
    private String respondedAt;
    private String creatorId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
