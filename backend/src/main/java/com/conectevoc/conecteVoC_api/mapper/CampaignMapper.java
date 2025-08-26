package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.campaign.CampaignResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.campaign.CreateCampaignDTO;
import com.conecteVoC.conecteVoC_api.dto.contact.ContactResponseDTO;
import com.conecteVoC.conecteVoC_api.entity.Campaign;
import com.conecteVoC.conecteVoC_api.entity.Contact;
import com.conecteVoC.conecteVoC_api.entity.Survey;
import com.conecteVoC.conecteVoC_api.enums.CampaignStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class CampaignMapper {

    public static Campaign toCampaign(CreateCampaignDTO dto, Survey survey, Set<Contact> contacts) {
        return dto != null ? Campaign.builder()
                .title(dto.getTitle())
                .survey(survey)
                .contacts(contacts)
                .attempts(dto.getAttempts())
                .maxResponses(dto.getMaxResponses())
                .status(CampaignStatus.ofCode(dto.getStatusCode()))
                .build() : null;
    }

    public static CampaignResponseDTO toCampaignResponseDTO(Campaign campaign) {
        Set<ContactResponseDTO> contactResponseDTOS = campaign.getContacts().stream()
                .map(ContactMapper::toContactResponseDTO)
                .collect(Collectors.toSet());

        return campaign != null ? CampaignResponseDTO.builder()
                .id(campaign.getId())
                .title(campaign.getTitle())
                .survey(SurveyMapper.toSurveyResponseDTO(campaign.getSurvey()))
                .contacts(contactResponseDTOS)
                .attempts(campaign.getAttempts())
                .maxResponses(campaign.getMaxResponses())
                .status(campaign.getStatus())
                .statusDescription(campaign.getStatus().getDescription())
                .statusCode(campaign.getStatus().getCode())
                .sentAt(campaign.getSentAt())
                .respondedAt(campaign.getRespondedAt())
                .createdAt(campaign.getCreatedAt())
                .updatedAt(campaign.getUpdatedAt())
                .build() : null;
    }
}
