package com.smartpoke.api.feature.user.dto;

import com.smartpoke.api.feature.user.model.User;
import com.smartpoke.api.feature.user.model.Userinfo;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class UserInfoDto {
        private Integer sex;
        private Double height;
        private Double weight;
        private LocalDateTime birthdate;

        public Userinfo toEntity(){
                return Userinfo.builder()
                        .sex(this.sex)
                        .height(this.height)
                        .weight(this.weight)
                        .birthdate(this.birthdate)
                        .build();
        }

        public static UserInfoDto fromEntity(Userinfo userinfo){
                return UserInfoDto.builder()
                        .sex(userinfo.getSex())
                        .height(userinfo.getHeight())
                        .weight(userinfo.getWeight())
                        .birthdate(userinfo.getBirthdate())
                        .build();
        }

}
