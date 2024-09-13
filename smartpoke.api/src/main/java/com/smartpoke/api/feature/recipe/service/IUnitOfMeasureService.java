package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;

import java.util.List;

public interface IUnitOfMeasureService {

    UnitOfMeasure findOrCreateNewUnitOfMeasure(String name);
    UnitOfMeasure findByName(String name);

    List<UnitOfMeasure> findAll();
}
