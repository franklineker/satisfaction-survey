package com.conecteVoC.conecteVoC_api.controller;

import com.conecteVoC.conecteVoC_api.dto.campaign.CampaignResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.campaign.CreateCampaignDTO;
import com.conecteVoC.conecteVoC_api.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignResponseDTO> createCampaign(@RequestBody CreateCampaignDTO dto){
        System.out.println(dto);
        CampaignResponseDTO responseDTO = campaignService.createCampaign(dto);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignResponseDTO> findById(@PathVariable UUID id) {
        CampaignResponseDTO responseDTO = campaignService.findById(id);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CampaignResponseDTO>> findCampaigns(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize)
    {
        Page<CampaignResponseDTO> responseDTOS = campaignService.findCampaigns(pageNumber, pageSize);

        return ResponseEntity.ok(responseDTOS);
    }

    @PostMapping(value = "/{campaignId}/contacts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignResponseDTO> addContactsToCampaign(@RequestBody Set<UUID> contactIds, @PathVariable UUID campaignId){
        CampaignResponseDTO responseDTO = campaignService.addContactsToCampaign(contactIds, campaignId);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(value = "/{campaignId}/contacts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> removeContactsFromCampaign(@RequestBody Set<UUID> contactIds, @PathVariable UUID campaignId){
        campaignService.removeContactsFromCampaign(contactIds, campaignId);

        return ResponseEntity.noContent().build();
    }

}
