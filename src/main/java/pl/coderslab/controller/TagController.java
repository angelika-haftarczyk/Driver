package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;

import java.util.List;

@Controller
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Tag> getAll() {
        return tagService.findAll();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Tag add(Tag tag) {
        return tagService.addTag(tag);
    }

    @RequestMapping(value = "/delete/{tagId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
    }


}
