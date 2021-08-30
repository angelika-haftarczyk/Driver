package pl.coderslab.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.model.FileInfo;
import pl.coderslab.model.dto.FileInfoDto;
import pl.coderslab.repository.FileInfoRepository;
import pl.coderslab.service.StorageService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StorageServiceImpl implements StorageService {

    private final static String DIR = "files";

    private final static String PATH = DIR + "/";

    @Autowired
    private FileInfoRepository fileInfoRepository;


    @PostConstruct
    public void init() {
        File file = new File(DIR);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public String store(MultipartFile multipartFile) throws IOException {
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMDDHHmmssS"));
        File file = new File(PATH + fileName).getAbsoluteFile();
        multipartFile.transferTo(file);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalFileName(multipartFile.getOriginalFilename());
        fileInfo.setFileName(file.getName());
        fileInfo.setContentType(multipartFile.getContentType());
        fileInfoRepository.save(fileInfo);
        return fileName;
    }

    @Override
    public FileInfoDto loadAsResource(String filename) {
        FileInfoDto fileInfoDto = new FileInfoDto();
        File file = new File(PATH + filename).getAbsoluteFile();
        Resource resource = new FileSystemResource(file);
        FileInfo fileInfo = fileInfoRepository.findByFileName(filename);
        fileInfoDto.setFile(resource);
        fileInfoDto.setFileName(filename);
        fileInfoDto.setOriginalFileName(fileInfo.getOriginalFileName());
        fileInfoDto.setContentType(fileInfo.getContentType());
        return fileInfoDto;
    }


}
