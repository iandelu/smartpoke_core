package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;

public interface IUnitOfMeasureService {

    UnitOfMeasure findOrCreateNewUnitOfMeasure(String name);
    UnitOfMeasure findByName(String name);
}
