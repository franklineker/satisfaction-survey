package com.conecteVoC.conecteVoC_api.controller;

import com.conecteVoC.conecteVoC_api.dto.survey.CreateSurveyDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.SurveyResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.UpdateSurveyDTO;
import com.conecteVoC.conecteVoC_api.service.SurveyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/surveys")
@CrossOrigin("*")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyResponseDTO> createSurvey(@RequestBody @Valid CreateSurveyDTO dto) {
        SurveyResponseDTO responseDTO = surveyService.create(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SurveyResponseDTO> getSurveyById(@PathVariable String id){
        SurveyResponseDTO responseDTO = surveyService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<SurveyResponseDTO>> findAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        Page<SurveyResponseDTO> responseDTOS = surveyService.findSurveys(pageNumber, pageSize);

        return ResponseEntity.ok(responseDTOS);
    }

    @PatchMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyResponseDTO> updateSurvey(@RequestBody UpdateSurveyDTO dto, @PathVariable UUID id){
        System.out.println(dto);
        System.out.println(String.format("Survey id: %s", id));

        SurveyResponseDTO responseDTOS = surveyService.updateSurvey(id, dto);

        return ResponseEntity.ok(responseDTOS);
    }

}
