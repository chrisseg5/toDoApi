package com.example.test2.service.impl;

import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Grading;
import com.example.test2.model.Question;
import com.example.test2.model.Questionnaire;
import com.example.test2.repository.GradingRepository;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.repository.QuestionnaireRepository;
import com.example.test2.service.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradingServiceImpl implements GradingService {
    @Autowired
    private GradingRepository gradingRepository;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public void addGrade(Questionnaire questionnaire, Question question, int grade) {
        Grading grading = new Grading();
        grading.setQuestionnaire(questionnaire);
        grading.setQuestion(question);
        grading.setGrade(grade);
        gradingRepository.save(grading);
    }






}
