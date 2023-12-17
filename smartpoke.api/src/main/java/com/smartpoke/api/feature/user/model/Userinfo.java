package com.smartpoke.api.feature.user.model;

// ... import statements
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class Userinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String phone;
    private Integer sex;
    private Double height;
    private Double weight;
    private LocalDateTime birthdate;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

}