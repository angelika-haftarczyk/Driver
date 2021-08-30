package pl.coderslab.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Tip extends BaseEntity {

    private String title;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private int numberOfRecommend;

    @ManyToMany
    private List<Tag> tag;

    @ManyToOne
    private Quiz quiz;

    private String videoUrl;
    @ManyToOne
    private FileInfo fileInfo;
}
