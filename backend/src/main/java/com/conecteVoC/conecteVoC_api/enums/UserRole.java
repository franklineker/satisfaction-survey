package com.conecteVoC.conecteVoC_api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum UserRole {
    USER(1000, "USER"),
    ADMIN(1001, "ADMIN");

    private int code;
    private String description;

    public static UserRole ofCode(Integer code) {
        return Stream.of(values())
                .filter(userRole -> Objects.equals(code, userRole.code))
                .findAny()
                .orElse(null);
    }

    public static UserRole ofDescription(String description){
        return Stream.of(values())
                .filter(userRole -> Objects.equals(description, userRole.description))
                .findAny()
                .orElse(null);
    }
}
