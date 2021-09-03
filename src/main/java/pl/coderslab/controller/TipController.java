package pl.coderslab.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Pokaż wszystkie porady")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TipDto> getAll() {
        return tipService.findAll();
    }

    @ApiOperation(value = "Dodaj nową poradę")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public TipDto add(TipDto tipDto, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if(file != null && !file.isEmpty()) {
            String store = storageService.store(file);
            tipDto.setFileName(store);
        }
        return tipService.addTip(tipDto);
    }

    @ApiOperation(value = "Usuń poradę", notes = "Usuwanie porady za pomocą ID")
    @RequestMapping(value = "/delete/{tipId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long tipId) {
        tipService.deleteTip(tipId);
    }

    @ApiOperation(value = "Edycja porady", notes = "Edycja porady wymaga wprowadzenia ID")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public TipDto edit(TipDto tipDto) {
        return tipService.editTip(tipDto);
    }

}
