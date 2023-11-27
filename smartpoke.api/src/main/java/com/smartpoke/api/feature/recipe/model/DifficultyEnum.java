package com.smartpoke.api.feature.recipe.model;

public enum DifficultyEnum {
    EASY, MODERATE, KIND_OF_HARD, HARD;

    public static DifficultyEnum calculateDifficult(Integer prepTime) {
        if (prepTime < 30){
            return EASY;
        }else if (prepTime <=60){
            return MODERATE;
        }else if (prepTime <=180){
            return  KIND_OF_HARD;
        }else{
            return HARD;
        }

    }
}
