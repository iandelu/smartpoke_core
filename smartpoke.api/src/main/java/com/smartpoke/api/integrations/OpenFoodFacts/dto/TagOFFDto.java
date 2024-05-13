package com.smartpoke.api.integrations.OpenFoodFacts.dto;

import com.smartpoke.api.feature.category.model.Tag;
import lombok.Data;

@Data
public class TagOFFDto {
    private String name;
    private String lan;

    public TagOFFDto(String offTag) {
        textToTag(offTag);
    }

    private void textToTag(String message){
        String[] parts = message.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de ingrediente inválido");
        }

        this.lan = parts[0].trim();
        this.name = parts[1].trim();
    }

    public Tag toEntity(){
        Tag tag = new Tag();
        tag.setLan(this.lan);
        tag.setName(this.name);

        return tag;
    }

}
