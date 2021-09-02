package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.QuizQuestion;

public interface QuizRepository extends JpaRepository<QuizQuestion, Long> {
}
