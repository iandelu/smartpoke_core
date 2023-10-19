package com.smartpoke.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserInfoDto {
        private Integer idUser;
        private String name;
        private String surname;
        private String phone;
        private Integer sex;
        private Integer height;
        private BigDecimal weight;
        private String email;
        private LocalDateTime birthDate;

}
