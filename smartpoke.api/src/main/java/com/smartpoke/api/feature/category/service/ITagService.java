package com.smartpoke.api.feature.category.service;

import com.smartpoke.api.feature.category.model.Tag;

import java.util.Optional;

public interface ITagService {

    Tag createNewTag(String name, String lan);
    void deleteTag(String name);
    Optional<Tag> findByName(String name);

}
