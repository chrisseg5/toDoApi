package com.example.test2.service.impl;

import com.example.test2.dto.QuenstionnaireIndexDto;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Questionnaire;
import com.example.test2.repository.QuestionnaireRepository;
import com.example.test2.service.QuestionnaireService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Override
    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public QuenstionnaireIndexDto toDTO(Questionnaire questionnaire) {
        QuenstionnaireIndexDto dto = new QuenstionnaireIndexDto();
        BeanUtils.copyProperties(questionnaire, dto);
        dto.setName(questionnaire.getName());
        dto.setId(questionnaire.getQuestionnaireId());
        return dto;
    }

    @Override
    public List<QuenstionnaireIndexDto> allQuestionnaires() {
        List<QuenstionnaireIndexDto> questionnaires = new ArrayList<>();
        questionnaireRepository.findAll().stream().forEach(questionnaire -> questionnaires.add(toDTO(questionnaire)));
        return  questionnaires ;
    }

    @Override
    public Questionnaire getQuestionnaireById(long id) {
        return questionnaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("questionnaire", "Id", id));
    }

    @Override
    public Questionnaire updateQuestionnaire(long id) {
        return questionnaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("questionnaire","Id",id) );
    }
}
