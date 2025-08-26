package com.conecteVoC.conecteVoC_api.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionDTO {

    private UUID id;
    private String text;
    private Integer typeCode;
    private Boolean isRequired;
    private Integer displayOrder;
    private Integer scaleMin;
    private Integer scaleMax;
}
