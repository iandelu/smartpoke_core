package com.smartpoke.api.feature.user.dto;

import com.smartpoke.api.feature.user.model.Location;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationDto {
    private Integer idLocalization;
    private Integer idUser;
    private Double latitude;
    private Double longitude;
    private String city;
    private LocalDateTime lastChange;

    public Location toEntity(){
        return Location.builder()
                .city(this.city)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .lastChange(this.lastChange)
                .build();
    }
}
