package pl.coderslab.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Quiz extends BaseEntity {

    @OneToMany
    private List<QuizQuestion> quizQuestions;

}
