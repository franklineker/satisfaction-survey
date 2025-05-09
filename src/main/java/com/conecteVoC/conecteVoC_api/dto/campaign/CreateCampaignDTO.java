package com.conecteVoC.conecteVoC_api.dto.campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCampaignDTO {

    private String title;
    private UUID surveyId;
    private List<UUID> contactIds;
    private Integer attempts;
    private Integer maxResponses;
    private OffsetDateTime sentAt;
    private OffsetDateTime respondedAt;
    private Integer statusCode;
}
