package com.example.test2.service;

import com.example.test2.model.Answer;
import com.example.test2.model.Question;
import com.example.test2.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated

@Service

public interface AnswerService {

   public Answer saveAnswer (Answer answer);
   List<Answer> getAllAnswersById(long id);

}
