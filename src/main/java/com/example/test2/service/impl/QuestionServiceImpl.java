package com.example.test2.service.impl;
import com.example.test2.args.QuestionArgs;
import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Question;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        super();
        this.questionRepository = questionRepository;
    }


    @Override
    public QuestionIndexDto toDTO(Question question) {
        QuestionIndexDto dto = new QuestionIndexDto();
        BeanUtils.copyProperties(question, dto);
        dto.setQuestionText(question.getQuestionText());
        dto.setId(question.getId());
        return dto;
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<QuestionIndexDto> allQuestions() {
        List<QuestionIndexDto> questions = new ArrayList<>();
        questionRepository.findAll().stream().forEach(question -> questions.add(toDTO(question)));
        return questions;

    }



//    @Override
//    public List<Question> getAllQuestions() {
//        return questionRepository.findAll();
//    }
//
//    @Override
//    public Question getQuestionById(long id) {
//        Optional<Question> question= questionRepository.findById(id);
//        if(question.isPresent()){
//            return question.get();
//        }else {
//            throw new ResourceNotFoundException("question","Id",id);
//        }
//    }
//
//    @Override
//    public Question deleteQuestion(long id) {
////         return questionRepository.deleteById(id);
//        return null;
//    }
}
