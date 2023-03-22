package com.example.test2.service.impl;
import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.QQuestion;
import com.example.test2.model.Question;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.QuestionService;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Projections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Service
public class QuestionServiceImpl implements QuestionService {

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

    @Override
    public Question getQuestionById(long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("question", "Id", id));
    }

    @Override
    public Question updateQuestion(long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("question","Id",id) );
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
