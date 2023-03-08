package com.example.test2.service.impl;

import com.example.test2.model.Answer;
import com.example.test2.repository.AnswerRepository;
import com.example.test2.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        super();
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
}

