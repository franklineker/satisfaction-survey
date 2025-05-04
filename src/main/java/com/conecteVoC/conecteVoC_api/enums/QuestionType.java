package com.conecteVoC.conecteVoC_api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum QuestionType {
    YES_NO(2000, "Sim ou Não"),
    RATING_SCALE(2001, "Escala de Avaliação"),
    OPEN_TEXT(2002, "Texto Aberto"),
    SINGLE_CHOICE(2003, "Única Escolha"),
    MULTIPLE_CHOICE(2004, "Múltipla Escolha");

    private int code;
    private String description;

    public static QuestionType ofCode(Integer code) {
        return Stream.of(values())
                .filter(userRole -> Objects.equals(code, userRole.code))
                .findAny()
                .orElse(null);
    }

    public static QuestionType ofDescription(String description){
        return Stream.of(values())
                .filter(userRole -> Objects.equals(description, userRole.description))
                .findAny()
                .orElse(null);
    }
}
