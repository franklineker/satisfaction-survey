package com.conecteVoC.conecteVoC_api.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseDTO {

    private String id;
    private String title;
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
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
