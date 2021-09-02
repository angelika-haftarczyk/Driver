package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.model.dto.QuizDto;
import pl.coderslab.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public QuizDto add(QuizDto quizDto) {
        return quizService.addQuiz(quizDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<QuizDto> getAll() {
        return quizService.findAll();
    }

}
