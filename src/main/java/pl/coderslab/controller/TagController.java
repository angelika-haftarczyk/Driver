package pl.coderslab.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "Pokaż wszystkie TAGi")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Tag> getAll() {
        return tagService.findAll();
    }

    @ApiOperation(value = "Dodawanie TAGu")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Tag add(Tag tag) {
        return tagService.addTag(tag);
    }

    @ApiOperation(value = "Usuwanie Tagu", notes = "Usuwanie TAGu za pomocą ID")
    @RequestMapping(value = "/delete/{tagId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
    }


}
