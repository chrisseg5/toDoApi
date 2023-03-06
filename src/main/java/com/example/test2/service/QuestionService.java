package com.example.test2.service;

import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
@Service
public interface QuestionService {
    QuestionIndexDto toDTO(Question question);
    Question saveQuestion (Question question);
    List<QuestionIndexDto> allQuestions();
//    List<Question> getAllQuestions();
//    Question getQuestionById(long id);
//    Question deleteQuestion(long id);
}
