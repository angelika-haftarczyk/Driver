package pl.coderslab.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
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
