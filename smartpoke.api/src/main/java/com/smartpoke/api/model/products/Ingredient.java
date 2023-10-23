package com.smartpoke.api.model.products;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Ingredient {

    @Id
    private Long id;
    
    private String name;
    private String language;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> products = new HashSet<>();

}