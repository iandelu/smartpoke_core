package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.feature.product.model.Allergen;

import java.util.Optional;

public interface IAllergenService {

    Allergen saveAllergen(String name, String lan);
    void deleteAllergen(String name);
    Optional<Allergen> findByName(String name);
}
