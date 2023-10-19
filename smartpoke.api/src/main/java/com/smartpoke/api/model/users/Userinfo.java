package com.smartpoke.api.model.users;

// ... import statements
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Userinfo {

    @Id
    private Long id;

    private String name;
    private String surname;
    private String phone;
    private Integer sex;
    private Integer height;
    private BigDecimal weight;
    private String email;
    private LocalDateTime birthdate;

}