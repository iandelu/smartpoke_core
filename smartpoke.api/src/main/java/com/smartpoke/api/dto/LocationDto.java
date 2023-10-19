package com.smartpoke.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationDto {
    private Integer idLocalization;
    private Integer idUser;
    private Integer latitude;
    private Integer longitude;
    private String city;
    private LocalDateTime lastChange;
}
