package com.smartpoke.api.feature.user.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class Location {

    @Id
    private Long id;

    private Long idUser;
    private Double latitude;
    private Double longitude;
    private String city;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime lastChange;
}
