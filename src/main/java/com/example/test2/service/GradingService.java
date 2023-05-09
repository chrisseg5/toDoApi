package com.example.test2.service;

import com.example.test2.model.Grading;
import com.example.test2.model.Question;
import com.example.test2.model.Questionnaire;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public interface GradingService {
    void addGrade(Questionnaire questionnaire, Question question, int grade) ;
}
