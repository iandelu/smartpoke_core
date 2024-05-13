package com.smartpoke.api.feature.category.service;

import com.smartpoke.api.feature.category.model.Tag;

import java.util.List;
import java.util.Optional;

public interface ITagService {

    Tag saveTag(String name, String lan);

    List<Tag> saveAllTags(List<Tag> tags);

    void deleteTag(String name);
    Optional<Tag> findByName(String name);

}
