package com.conecteVoC.conecteVoC_api.dto.campaign;

import com.conecteVoC.conecteVoC_api.dto.contact.ContactResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.SurveyResponseDTO;
import com.conecteVoC.conecteVoC_api.enums.CampaignStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponseDTO {

    private UUID id;
    private String title;
    private SurveyResponseDTO survey;
    private Set<ContactResponseDTO> contacts;
    private Integer attempts;
    private CampaignStatus status;
    private Integer statusCode;
    private String statusDescription;
    private Integer maxResponses;
    private OffsetDateTime sentAt;
    private OffsetDateTime respondedAt;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
