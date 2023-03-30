package com.example.test2.service;

import com.example.test2.args.QuestionArgs;
import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public interface QuestionService {
    QuestionIndexDto toDTO(Question question);

    Question saveQuestion(Question question);

    List<QuestionIndexDto> allQuestions();

    Question getQuestionById(long id);

    Question updateQuestion(long id);


//    Page<QuestionIndexDto> questionIndex(QuestionArgs args, Pageable pageable);

//    List<Question> getAllQuestions();
}
