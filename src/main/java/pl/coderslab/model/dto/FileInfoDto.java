package pl.coderslab.model.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class FileInfoDto {
    Resource file;
    private String originalFileName;
    private String fileName;
    private String contentType;
}
