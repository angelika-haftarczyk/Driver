package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.TagRepository;
import pl.coderslab.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Tag findByNameOrCreate(String name) {
        Tag tag = tagRepository.findByName(name);
        if(tag == null) {
            tag = new Tag();
            tag.setName(name);
            tagRepository.save(tag);
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long tagId) {
        tagRepository.delete(tagId);
    }


}
