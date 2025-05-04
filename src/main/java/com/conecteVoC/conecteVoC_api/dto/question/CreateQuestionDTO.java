package com.conecteVoC.conecteVoC_api.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionDTO {
    private String text;
    private Integer typeCode;
    private boolean isRequired;
    private Integer displayOrder;
    private Integer scaleMin;
    private Integer scaleMax;
}

