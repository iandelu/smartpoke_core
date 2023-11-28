package com.smartpoke.api.feature.user.dto;

import com.smartpoke.api.feature.user.model.User;
import com.smartpoke.api.feature.user.model.Userinfo;
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
        private Double height;
        private Double weight;
        private LocalDateTime birthDate;

        public Userinfo toEntity(){
                return Userinfo.builder()
                        .name(this.name)
                        .surname(this.surname)
                        .phone(phone)
                        .sex(this.sex)
                        .height(this.height)
                        .weight(this.weight)
                        .birthdate(this.birthDate)
                        .build();
        }

}
