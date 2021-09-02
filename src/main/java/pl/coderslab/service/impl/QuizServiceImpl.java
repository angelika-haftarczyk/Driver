package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.FileInfo;
import pl.coderslab.model.QuizQuestion;
import pl.coderslab.model.Tip;
import pl.coderslab.model.dto.QuizDto;
import pl.coderslab.repository.FileInfoRepository;
import pl.coderslab.repository.QuizRepository;
import pl.coderslab.repository.TipRepository;
import pl.coderslab.service.QuizService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    TipRepository tipRepository;

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Override
    public QuizDto addQuiz(QuizDto quizDto) {
        QuizQuestion quizQuestion = fromDto(quizDto);
        quizQuestion = quizRepository.save(quizQuestion);
        if(quizDto.getTipId() != null) {
            Tip tip = tipRepository.findOne(quizDto.getTipId());
            if(tip != null) {
                tip.getQuizQuestions().add(quizQuestion);
                tipRepository.save(tip);
            }
        }
        return toDto(quizQuestion);
    }

    @Override
    public void deleteQuiz(Long id) {
        QuizQuestion one = quizRepository.getOne(id);
        List<Tip> tips = tipRepository.findByQuizQuestionsId(id);
        for (Tip tip:tips){
            tip.getQuizQuestions().remove(one);
            tipRepository.save(tip);
        }
        quizRepository.delete(id);
    }

    @Override
    public List<QuizDto> findAll() {
        return quizRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<QuizDto> findByTipId(Long id) {
        return tipRepository.getOne(id).getQuizQuestions().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public QuizDto editQuiz(QuizDto quizDto) {
        QuizQuestion quizQuestion = fromDto(quizDto);
        quizQuestion = quizRepository.save(quizQuestion);
        return toDto(quizQuestion);
    }

    private QuizQuestion fromDto(QuizDto quizDto) {
        QuizQuestion quizQuestion;
        if(quizDto.getId() != null) {
            quizQuestion = quizRepository.findOne(quizDto.getId());
        } else {
            quizQuestion = new QuizQuestion();
        }
        quizQuestion.setQuestion(quizDto.getQuestion());
        quizQuestion.setAnswer1(quizDto.getAnswer1());
        quizQuestion.setAnswer2(quizDto.getAnswer2());
        quizQuestion.setAnswer3(quizDto.getAnswer3());

        if(quizDto.getQuestionFile() != null){
            FileInfo fileInfo = fileInfoRepository.findByFileName(quizDto.getQuestionFile());
            quizQuestion.setQuestionFile(fileInfo);
        }

        quizQuestion.setQuestionUrl(quizDto.getQuestionUrl());

        if(quizDto.getAnswer1File() != null){
            FileInfo fileInfo = fileInfoRepository.findByFileName(quizDto.getAnswer1File());
            quizQuestion.setAnswer1File(fileInfo);
        }

        if(quizDto.getAnswer2File() != null){
            FileInfo fileInfo = fileInfoRepository.findByFileName(quizDto.getAnswer2File());
            quizQuestion.setAnswer2File(fileInfo);
        }

        if(quizDto.getAnswer3File() != null){
            FileInfo fileInfo = fileInfoRepository.findByFileName(quizDto.getAnswer3File());
            quizQuestion.setAnswer3File(fileInfo);
        }

        quizQuestion.setCorrectAnswer(quizDto.getCorrectAnswer());
        return quizQuestion;
    }

    private QuizDto toDto(QuizQuestion quizQuestion) {
        QuizDto quizDto = new QuizDto();
        quizDto.setId(quizQuestion.getId());
        quizDto.setQuestion(quizQuestion.getQuestion());
        quizDto.setAnswer1(quizQuestion.getAnswer1());
        quizDto.setAnswer2(quizQuestion.getAnswer2());
        quizDto.setAnswer3(quizQuestion.getAnswer3());
        quizDto.setCorrectAnswer(quizQuestion.getCorrectAnswer());
        return quizDto;
    }

}


