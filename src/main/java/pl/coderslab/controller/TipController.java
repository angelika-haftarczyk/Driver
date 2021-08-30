package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.model.dto.TipDto;
import pl.coderslab.service.StorageService;
import pl.coderslab.service.TipService;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/tip")
@RestController
public class TipController {

    @Autowired
    private StorageService storageService;

    @Autowired
    TipService tipService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TipDto> getAll() {
        return tipService.findAll();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public TipDto add(TipDto tipDto, @RequestParam("file") MultipartFile file) throws IOException {
        if(file != null && !file.isEmpty()) {
            String store = storageService.store(file);
            tipDto.setFileName(store);
        }
        return tipService.addTip(tipDto);
    }

    @RequestMapping(value = "/delete/{tipId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long tipId) {
        tipService.deleteTip(tipId);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public TipDto edit(TipDto tipDto) {
        return tipService.editTip(tipDto);
    }

}
