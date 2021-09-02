package pl.coderslab.service;

import pl.coderslab.model.dto.QuizDto;

import java.util.List;

public interface QuizService {
    QuizDto addQuiz(QuizDto quiz);
    void deleteQuiz(Long id);
    List<QuizDto> findAll();
    List<QuizDto> findByTipId(Long id);
    QuizDto editQuiz(QuizDto quizDto);
}
