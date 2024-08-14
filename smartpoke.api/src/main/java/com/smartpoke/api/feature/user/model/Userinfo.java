package com.smartpoke.api.feature.user.model;

// ... import statements
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer sex;
    private Double height;
    private Double weight;
    private LocalDateTime birthdate;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

}