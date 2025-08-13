package com.conecteVoC.conecteVoC_api.service;

import com.conecteVoC.conecteVoC_api.dto.campaign.CampaignResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.campaign.CreateCampaignDTO;
import com.conecteVoC.conecteVoC_api.entity.Campaign;
import com.conecteVoC.conecteVoC_api.entity.Contact;
import com.conecteVoC.conecteVoC_api.entity.Survey;
import com.conecteVoC.conecteVoC_api.exceptions.CampaignPartialSuccessException;
import com.conecteVoC.conecteVoC_api.mapper.CampaignMapper;
import com.conecteVoC.conecteVoC_api.repository.CampaignRepository;
import com.conecteVoC.conecteVoC_api.repository.ContactRepository;
import com.conecteVoC.conecteVoC_api.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final SurveyRepository surveyRepository;
    private final ContactRepository contactRepository;
    private final JdbcTemplate jdbcTemplate;


    @Transactional
    public CampaignResponseDTO createCampaign(CreateCampaignDTO dto){
        Survey survey = surveyRepository.findById(dto.getSurveyId())
                .orElseThrow(() -> new RuntimeException(String.format("Cannot create campaign. Survey with id <%s> not found.", dto.getSurveyId())));

        Set<Contact> foundContacts = contactRepository.findAllById(dto.getContactIds())
                .stream().collect(Collectors.toSet());

        Campaign campaign = CampaignMapper.toCampaign(dto, survey, foundContacts);

        if(foundContacts.size() < dto.getContactIds().size()){
            Set<UUID> notFound = new HashSet<>(dto.getContactIds());
            foundContacts.forEach(contact -> notFound.remove(contact.getId()));

            throw new CampaignPartialSuccessException(notFound);
        }

        return CampaignMapper.toCampaignResponseDTO(campaignRepository.save(campaign));
    }

    @Transactional
    public CampaignResponseDTO findById(UUID id){
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Campaign with id <%s> not found.", id)));

        return CampaignMapper.toCampaignResponseDTO(campaign);
    }

    @Transactional
    public Page<CampaignResponseDTO> findCampaigns(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("createdAt")));
        Page<Campaign> campaignPage = campaignRepository.findAll(pageable);

        if(pageNumber >= campaignPage.getTotalPages() && campaignPage.getTotalPages() > 0){
            return Page.empty(pageable);
        }

        Page<CampaignResponseDTO> responseDTO = campaignPage.map(CampaignMapper::toCampaignResponseDTO);

        return responseDTO;
    }

    public void removeContactsFromCampaign(Set<UUID> contactIds, UUID campaignId){
        if(campaignId == null || contactIds.isEmpty()) return;

        String placeholders = contactIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));

        String sql = "DELETE FROM campaign_contact WHERE campaign_id = ? AND contact_id IN ("+placeholders+")";

        List<Object> params = new ArrayList<>();
        params.add(campaignId);
        params.addAll(contactIds);

        jdbcTemplate.update(sql, params.toArray());
    }

    @Transactional
    public CampaignResponseDTO addContactsToCampaign(Set<UUID> contactIds, UUID campaignId){
        if(campaignId == null || contactIds.isEmpty()) {
            throw new IllegalArgumentException("Campaign ID and contact IDs cannot be null or empty");
        };

        String valuesSql = contactIds.stream()
                .map(id -> "(?,?)")
                .collect(Collectors.joining(", "));

        String sql = "INSERT INTO campaign_contact (campaign_id, contact_id) VALUES "+ valuesSql;

        List<Object> params = new ArrayList<>();
        for(UUID contactId : contactIds){
            params.add(campaignId);
            params.add(contactId);
        }

        jdbcTemplate.update(sql, params.toArray());

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found after update."));

        return CampaignMapper.toCampaignResponseDTO(campaign);
    }

}
