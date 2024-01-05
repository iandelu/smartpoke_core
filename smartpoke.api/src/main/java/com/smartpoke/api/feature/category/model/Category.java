package com.smartpoke.api.feature.category.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Category {

    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String iconPath;
    private String color;
    @Column
    private String lan;



}
