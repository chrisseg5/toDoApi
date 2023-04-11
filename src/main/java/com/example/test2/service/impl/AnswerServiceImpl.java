package com.example.test2.service.impl;


import com.example.test2.model.Answer;
import com.example.test2.repository.AnswerRepository;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.AnswerService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        super();
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> getAllAnswersById(long id) {

        return answerRepository.findByQuestionId(id);
    }


}

