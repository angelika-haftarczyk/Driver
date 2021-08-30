package pl.coderslab.model;


import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class FileInfo extends BaseEntity {

    private String originalFileName;
    private String fileName;
    private String contentType;
//    String pathImage;
//    String pathVideo;

}
