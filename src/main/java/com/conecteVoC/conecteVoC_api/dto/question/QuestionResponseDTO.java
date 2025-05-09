package com.conecteVoC.conecteVoC_api.dto.question;

import com.conecteVoC.conecteVoC_api.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDTO {

    private String id;
    private String surveyId;
    private String text;
    private QuestionType type;
    private String typeLabel;
    private Integer typeCode;
    private boolean isRequired;
    private Integer displayOrder;
    private Integer scaleMin;
    private Integer scaleMax;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
