package com.conecteVoC.conecteVoC_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @ManyToMany
    @JoinColumn(name = "recipient_id")
    private List<Recipient> recipients;
}
