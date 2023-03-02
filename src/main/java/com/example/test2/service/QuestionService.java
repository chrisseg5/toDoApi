package com.example.test2.service;

import com.example.test2.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface QuestionService {
    Question saveQuestion (Question question);
    List<Question> getAllQuestions();
    Question getQuestionById(long id);
    Question deleteQuestion(long id);
}
