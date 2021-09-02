package pl.coderslab.service;


import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.model.dto.FileInfoDto;

import java.io.IOException;


public interface StorageService {


    String store(MultipartFile file) throws IOException;

    FileInfoDto loadAsResource(String filename);

}