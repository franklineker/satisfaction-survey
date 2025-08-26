package com.conecteVoC.conecteVoC_api.entity;

import com.conecteVoC.conecteVoC_api.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString(exclude = "survey")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    private String text;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @Column(name = "is_required")
    private boolean isRequired;
    @Column(name = "display_order")
    private Integer displayOrder;
    @Column(name = "scale_min")
    private Integer scaleMin;
    @Column(name = "scale_max")
    private Integer scaleMax;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

