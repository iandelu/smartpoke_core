package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.product.model.Allergen;
import com.smartpoke.api.feature.product.repository.AllergenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AllergenService implements IAllergenService{

    @Autowired
    private AllergenRepository allergenRepository;

    @Override
    public Allergen saveAllergen(String name, String lan) {
        return allergenRepository.findByName(name, lan)
                .orElseGet(() -> {
                    Allergen newAllergen = new Allergen();
                    newAllergen.setName(name);
                    newAllergen.setLan(lan);
                    return allergenRepository.save(newAllergen);
                });
    }

    @Override
    public void deleteAllergen(String name) {
        allergenRepository.deleteByName(name);
    }

    @Override
    public Optional<Allergen> findByName(String name) {
        return Optional.empty();
    }
}
