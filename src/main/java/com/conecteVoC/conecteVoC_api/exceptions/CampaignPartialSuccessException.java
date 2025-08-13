package com.conecteVoC.conecteVoC_api.exceptions;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class CampaignPartialSuccessException extends RuntimeException{

    private final Set<UUID> failed;

    public CampaignPartialSuccessException(Set<UUID> failed){
        super("Some contacts were not found");
        this.failed = failed;
    }
}
