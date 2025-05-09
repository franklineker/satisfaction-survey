package com.conecteVoC.conecteVoC_api.service;

import com.conecteVoC.conecteVoC_api.dto.contact.ContactResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.contact.CreateContactDTO;
import com.conecteVoC.conecteVoC_api.dto.contact.UpdateContactDTO;
import com.conecteVoC.conecteVoC_api.entity.ActiveContact;
import com.conecteVoC.conecteVoC_api.entity.Contact;
import com.conecteVoC.conecteVoC_api.mapper.ContactMapper;
import com.conecteVoC.conecteVoC_api.repository.ActiveContactRepository;
import com.conecteVoC.conecteVoC_api.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ActiveContactRepository activeContactRepository;

    public ContactResponseDTO createContact(CreateContactDTO dto) {
        Contact contact = contactRepository.save(ContactMapper.fromCreateContactDTO(dto));

        return ContactMapper.toContactResponseDTO(contact);
    }

    public ContactResponseDTO findById(UUID id){
        ActiveContact activeContact = activeContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Contact with id <%s> not found.", id)));

        return ContactMapper.toContactResponseDTO(activeContact);
    }

    public Page<ContactResponseDTO> findContacts(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("createdAt")));
        Page<ActiveContact> contactPage = activeContactRepository.findAll(pageable);

        if(pageNumber > contactPage.getTotalPages() && contactPage.getTotalPages() > 0){
            return Page.empty(pageable);
        }

        Page<ContactResponseDTO> responseDTOS = contactPage.map(ContactMapper::toContactResponseDTO);

        return responseDTOS;
    }

    public ContactResponseDTO updateContact(UpdateContactDTO dto, UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Contact with id <%s> not found.", id)));

        ContactMapper.fromUpdateContactDTO(dto, contact);

        ContactResponseDTO responseDTO = ContactMapper.toContactResponseDTO(contactRepository.save(contact));

        return responseDTO;
    }
}
