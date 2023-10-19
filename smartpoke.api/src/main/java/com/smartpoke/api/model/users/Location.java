package com.smartpoke.api.model.users;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
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
