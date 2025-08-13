package com.conecteVoC.conecteVoC_api.controller;

import com.conecteVoC.conecteVoC_api.dto.contact.ContactResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.contact.CreateContactDTO;
import com.conecteVoC.conecteVoC_api.dto.contact.UpdateContactDTO;
import com.conecteVoC.conecteVoC_api.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/contacts")
public class ContactController {

    private final ContactService contactService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactResponseDTO> createContact(@RequestBody CreateContactDTO dto){
        ContactResponseDTO responseDTO = contactService.createContact(dto);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContactResponseDTO> findById(@PathVariable UUID id){
        ContactResponseDTO responseDTO = contactService.findById(id);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ContactResponseDTO>> findContacts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Page<ContactResponseDTO> responseDTOS = contactService.findContacts(pageNumber, pageSize);

        return ResponseEntity.ok(responseDTOS);
    }

    @PatchMapping(value = "/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactResponseDTO> updateContact(@PathVariable UUID id, @RequestBody UpdateContactDTO dto){
        ContactResponseDTO responseDTO = contactService.updateContact(dto,id);

        return ResponseEntity.ok(responseDTO);
    }

}
