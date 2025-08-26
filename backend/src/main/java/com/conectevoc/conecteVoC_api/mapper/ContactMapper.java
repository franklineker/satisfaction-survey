package com.conecteVoC.conecteVoC_api.mapper;

import com.conecteVoC.conecteVoC_api.dto.contact.ContactResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.contact.CreateContactDTO;
import com.conecteVoC.conecteVoC_api.dto.contact.UpdateContactDTO;
import com.conecteVoC.conecteVoC_api.entity.ActiveContact;
import com.conecteVoC.conecteVoC_api.entity.Contact;

import java.util.Optional;

public class ContactMapper {

    public static Contact fromCreateContactDTO(CreateContactDTO dto) {
        return dto != null ? Contact.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .taxNumber(dto.getTaxNumber())
                .build(): null;
    }

    public static Contact fromUpdateContactDTO(UpdateContactDTO dto, Contact contact){
        if(dto.getFirstName() != null) contact.setFirstName(dto.getFirstName());
        if(dto.getLastName() != null) contact.setLastName(dto.getLastName());
        if(dto.getEmail() != null) contact.setEmail(dto.getEmail());
        if(dto.getPhoneNumber() != null) contact.setPhoneNumber(dto.getPhoneNumber());
        if(dto.getTaxNumber() != null) contact.setTaxNumber(dto.getTaxNumber());

        return contact;
    }

    public static ContactResponseDTO toContactResponseDTO(Contact contact){
        String firstName = Optional.ofNullable(contact.getFirstName()).orElse("");
        String lastName = Optional.ofNullable(contact.getLastName()).orElse("");

        return contact != null ? ContactResponseDTO.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .fullName((firstName + " " + lastName).trim())
                .phoneNumber(contact.getPhoneNumber())
                .taxNumber(contact.getTaxNumber())
                .email(contact.getEmail())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build() : null;
    }

    public static ContactResponseDTO toContactResponseDTO(ActiveContact activeContact){
        String firstName = Optional.ofNullable(activeContact.getFirstName()).orElse("");
        String lastName = Optional.ofNullable(activeContact.getLastName()).orElse("");

        return activeContact != null ? ContactResponseDTO.builder()
                .id(activeContact.getId())
                .firstName(activeContact.getFirstName())
                .lastName(activeContact.getLastName())
                .fullName((firstName + "  " + lastName).trim())
                .phoneNumber(activeContact.getPhoneNumber())
                .taxNumber(activeContact.getTaxNumber())
                .email(activeContact.getEmail())
                .createdAt(activeContact.getCreatedAt())
                .updatedAt(activeContact.getUpdatedAt())
                .build() : null;
    }

}
