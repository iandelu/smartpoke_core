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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitude;
    private Double longitude;
    private String city;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime lastChange;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}
