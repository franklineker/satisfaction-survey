package com.conecteVoC.conecteVoC_api.dto.survey;

import com.conecteVoC.conecteVoC_api.dto.question.QuestionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

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
    private boolean isAnonymous;
    private LocalDateTime sentAt;
    private LocalDateTime respondedAt;
    private String creatorId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<QuestionResponseDTO> questions;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
