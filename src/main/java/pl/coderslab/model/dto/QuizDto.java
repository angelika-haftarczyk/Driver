package pl.coderslab.model.dto;

import lombok.Data;

@Data
public class QuizDto {

    private Long id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private int correctAnswer;
    private Long tipId;

}
