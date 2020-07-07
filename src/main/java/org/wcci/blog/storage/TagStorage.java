package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.TagRepository;

@Service
public class TagStorage {

    TagRepository tagRepo;

    public TagStorage(TagRepository tagRepo){
        this.tagRepo = tagRepo;
    }

    public Iterable<Tag> findAllTags() {
        return tagRepo.findAll();
    }

    public Tag findByName(String name) {
        return tagRepo.findByName(name);
    }

    public boolean isTagExists(String name) {
        return tagRepo.existsByName(name);
    }

    public Tag findByID(Long id){ return tagRepo.findById(id).get();
    }

    public void saveTag(Tag tagToAdd) {
        tagRepo.save(tagToAdd);
    }
}
