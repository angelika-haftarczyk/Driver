package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.model.dto.QuizDto;
import pl.coderslab.model.dto.TipDto;
import pl.coderslab.service.QuizService;
import pl.coderslab.service.StorageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public QuizDto add(QuizDto quizDto,
                       @RequestParam(value = "quizFile", required = false) MultipartFile quizFile,
                       @RequestParam(value = "quiz1File", required = false) MultipartFile quiz1File,
                       @RequestParam(value = "quiz2File", required = false) MultipartFile quiz2File,
                       @RequestParam(value = "quiz3File", required = false) MultipartFile quiz3File) throws IOException {
        if(quizFile != null && !quizFile.isEmpty()) {
            String store = storageService.store(quizFile);
            quizDto.setQuestionFile(store);
        }
        if(quiz1File != null && !quiz1File.isEmpty()) {
            String store = storageService.store(quiz1File);
            quizDto.setAnswer1File(store);
        }
        if(quiz2File != null && !quiz2File.isEmpty()) {
            String store = storageService.store(quiz2File);
            quizDto.setAnswer2File(store);
        }
        if(quiz3File != null && !quiz3File.isEmpty()) {
            String store = storageService.store(quiz3File);
            quizDto.setAnswer3File(store);
        }
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

    @RequestMapping(value = "/byTip/{id}", method = RequestMethod.GET)
    public List<QuizDto> getByTipId(@PathVariable Long id) {
        return quizService.findByTipId(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public QuizDto edit(QuizDto quizDto) {
        return quizService.editQuiz(quizDto);
    }

}
