package com.smartpoke.api.feature.category.service;

import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.category.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService{

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(String name, String lan) {

        return findByName(name)
                .orElseGet( () -> {
                    Tag tag = new Tag();
                    tag.setName(name);
                    tag.setLan(lan);
                    return tagRepository.save(tag);
                }
        );
    }

    @Override
    public List<Tag> saveAllTags(List<Tag> tags) {
        return tags.stream().map(tag -> saveTag(tag.getName(), tag.getLan())).toList();
    }

    @Override
    public void deleteTag(String name) {
        Optional<Tag> tag = tagRepository.findByName(name);
        tag.ifPresent(tagRepository::delete);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }
}
