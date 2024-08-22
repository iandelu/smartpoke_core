package com.smartpoke.api.feature.unit_of_measure;

import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;
import com.smartpoke.api.feature.recipe.service.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/unit_of_measure")
public class UnitOfMeasureController {

    @Autowired
    UnitOfMeasureService unitOfMeasureService;

    @GetMapping
    public ResponseEntity<List<UnitOfMeasure>> getUnitOfMeasures() {
        return ResponseEntity.ok().body(unitOfMeasureService.findAll());
    }


}
