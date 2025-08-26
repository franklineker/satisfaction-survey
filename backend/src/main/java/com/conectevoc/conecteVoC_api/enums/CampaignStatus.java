package com.conecteVoC.conecteVoC_api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum CampaignStatus {

    CREATED(3000, "Criada"),
    SENT(3001, "Enviada"),
    RESPONDED(3002, "Respondida"),
    FAILED(3003, "Falhou"),
    CANCELED(3004, "Cancelada");

    private int code;
    private String description;

    public static CampaignStatus ofCode(Integer code) {
        return Stream.of(values())
                .filter(userRole -> Objects.equals(code, userRole.code))
                .findAny()
                .orElse(null);
    }

    public static CampaignStatus ofDescription(String description){
        return Stream.of(values())
                .filter(userRole -> Objects.equals(description, userRole.description))
                .findAny()
                .orElse(null);
    }
}
