package pl.coderslab.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class FileInfo extends BaseEntity {

    private String originalFileName;
    private String fileName;
    private String contentType;

}
