package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.exception.StorageFileNotFoundException;
import pl.coderslab.model.dto.FileInfoDto;
import pl.coderslab.service.StorageService;

import java.io.IOException;


@Controller
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    private StorageService storageService;


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        FileInfoDto fileInfoDto = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileInfoDto.getOriginalFileName() + "\"")
                .contentType(MediaType.valueOf(fileInfoDto.getContentType()))
                .body(fileInfoDto.getFile());
    }

    @PostMapping("/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                         RedirectAttributes redirectAttributes) throws IOException {

        return ResponseEntity.ok().body(storageService.store(file));
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}