package com.hehe.cost_control_api.model;

import com.hehe.cost_control_api.model.enums.TypeLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "min_score", nullable = false)
    private Float minScore;

    @Column(name = "max_score", nullable = false)
    private Float maxScore;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "message",  nullable = false, length = 255)
    private String message;

    @Column(name = "summary", nullable = false, length = 200)
    private String summary;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "type_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeLevel typeLevel;

    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }

}
