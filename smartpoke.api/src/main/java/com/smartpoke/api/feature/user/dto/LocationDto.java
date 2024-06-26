package com.smartpoke.api.feature.user.dto;

import com.smartpoke.api.feature.user.model.Location;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LocationDto {
    @Id
    private Integer idUser;
    private Integer idLocalization;
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

    public static LocationDto fromEntity(Location location) {
        return LocationDto.builder()
                .city(location.getCity())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .lastChange(location.getLastChange())
                .build();
    }
}
