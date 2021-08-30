package pl.coderslab.model;


import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class QuizQuestion extends BaseEntity{

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private int correctAnswer;
}
