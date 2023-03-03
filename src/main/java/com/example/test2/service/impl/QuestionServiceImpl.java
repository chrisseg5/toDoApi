package com.example.test2.service.impl;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Question;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.QuestionService;
import org.springframework.stereotype.Service;

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
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
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
