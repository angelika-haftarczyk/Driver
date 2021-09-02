package pl.coderslab.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class QuizQuestion extends BaseEntity{

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    @ManyToOne
    private FileInfo questionFile;
    private String questionUrl;
    @ManyToOne
    private FileInfo answer1File;
    @ManyToOne
    private FileInfo answer2File;
    @ManyToOne
    private FileInfo answer3File;
    private int correctAnswer;
}
