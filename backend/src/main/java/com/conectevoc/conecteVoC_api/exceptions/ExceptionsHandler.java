package com.conecteVoC.conecteVoC_api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(CampaignPartialSuccessException.class)
    public ResponseEntity<?> handleCampaignPartialSuccess(CampaignPartialSuccessException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("failed", ex.getFailed());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(207).body(body);
    }
}
