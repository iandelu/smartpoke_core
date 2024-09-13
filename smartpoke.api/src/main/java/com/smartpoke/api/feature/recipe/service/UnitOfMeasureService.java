package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;
import com.smartpoke.api.feature.recipe.repository.UnitOfMesureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitOfMeasureService implements IUnitOfMeasureService {

    @Autowired
    private UnitOfMesureRepository unitOfMeasureRepository;

    @Override
    public UnitOfMeasure findOrCreateNewUnitOfMeasure(String name) {
        return unitOfMeasureRepository.findByName(name)
                .orElseGet(() ->{
                    UnitOfMeasure unitOfMeasure= new UnitOfMeasure();
                    unitOfMeasure.setName(name);
                    return unitOfMeasureRepository.save(unitOfMeasure);
                });
    }

    @Override
    public UnitOfMeasure findByName(String unit) {
        return unitOfMeasureRepository.findByName(unit)
                .orElseGet(() -> findOrCreateNewUnitOfMeasure(unit));
    }

    @Override
    public List<UnitOfMeasure> findAll() {
        return unitOfMeasureRepository.findAll();
    }
}
