package pl.coderslab.service;

import pl.coderslab.model.Tag;
import pl.coderslab.model.dto.TipDto;

import java.util.List;

public interface TagService {
    Tag getTagByName(String name);
    Tag findByNameOrCreate(String name);
    List<Tag> findAll();
    Tag addTag(Tag tag);
    void deleteTag(Long tagId);
}
