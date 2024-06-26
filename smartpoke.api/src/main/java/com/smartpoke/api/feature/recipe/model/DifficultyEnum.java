package com.smartpoke.api.feature.recipe.model;

public enum DifficultyEnum {
    EASY, MODERATE, KIND_OF_HARD, HARD, ;

    public static DifficultyEnum calculateDifficult(Integer prepTime) {
        if (prepTime == null) return MODERATE;
        if (prepTime < 30){
            return EASY;
        }else if (prepTime <=60){
            return MODERATE;
        }else if (prepTime <=120){
            return  KIND_OF_HARD;
        }else{
            return HARD;
        }

    }
}
