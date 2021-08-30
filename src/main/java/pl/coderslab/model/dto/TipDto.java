package pl.coderslab.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class TipDto {

    private Long id;
    private String title;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private int numberOfRecommend;
    private List<String> tags;
    private String videoUrl;
    private String fileName;

}
