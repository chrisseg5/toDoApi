package com.example.test2.service.impl;
import com.example.test2.args.QuestionArgs;
import com.example.test2.dto.QuestionIndexDto;

import com.example.test2.model.QQuestion;
import com.example.test2.model.Question;

import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.QuestionService;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionServiceImpl implements QuestionService {

//    @Autowired
//
//    private JPAQueryFactory queryFactory;
    @Autowired
    private QuestionRepository questionRepository;



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



    /**
     * Ορισμός των πεδίων που έρχονται στο index dto
     */
    private FactoryExpression<QuestionIndexDto> questionIndexDtoExpression(QQuestion qQuestion) {
        return Projections.bean(QuestionIndexDto.class,

                qQuestion.questionText

        );
    }
//    @Override
//    public Page<QuestionIndexDto> questionIndex(QuestionArgs args, Pageable pageable) {
//        QQuestion qQuestion=QQuestion.question;
//        FactoryExpression<QuestionIndexDto> factoryExpression = questionIndexDtoExpression(qQuestion) ;
//        JPQLQuery<QuestionIndexDto> query = queryFactory
//                .select(factoryExpression)
//                .from(qQuestion);
//        return  questionRepository.findAll(query,pageable);
//    }





}
