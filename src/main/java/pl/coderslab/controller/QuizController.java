package pl.coderslab.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.model.dto.QuizDto;
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

    @ApiOperation(value = "Dodawanie quizu", notes = "Wprowadź nowy quiz")
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

    @ApiOperation(value = "Usuwanie quizu", notes = "Wybierz quiz do usunięcia po id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@ApiParam(value = "unikalne id Quizu") @PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    @ApiOperation(value = "Pokaż wszystkie quziy", notes = "Widzimy wszystkie quziy")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<QuizDto> getAll() {
        return quizService.findAll();
    }

    @ApiOperation(value = "Pokaż Quziy związene z daną poradą", notes = "Wproawdź ID porady, żeby zobaczyć interesujące Cię quziy")
    @RequestMapping(value = "/byTip/{id}", method = RequestMethod.GET)
    public List<QuizDto> getByTipId(@PathVariable Long id) {
        return quizService.findByTipId(id);
    }

    @ApiOperation(value = "Edycja quziu", notes = "Edycja quziu wymaga wprowadzenia ID")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public QuizDto edit(QuizDto quizDto) {
        return quizService.editQuiz(quizDto);
    }

}
