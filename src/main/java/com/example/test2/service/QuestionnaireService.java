package com.example.test2.service;
import com.example.test2.dto.index.QuenstionnaireIndexDto;
import com.example.test2.dto.mini.QuestionnaireMiniDo;
import com.example.test2.model.Questionnaire;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public interface QuestionnaireService {
    Questionnaire createQuestionnaire(Questionnaire questionnaire);
    QuenstionnaireIndexDto toDTO(Questionnaire questionnaire);

    QuestionnaireMiniDo toMiniDTO(Questionnaire questionnaire);

    List<QuenstionnaireIndexDto> allQuestionnaires();
    Questionnaire getQuestionnaireById(long id);
    Questionnaire updateQuestionnaire(long id);
    Questionnaire saveQ(Questionnaire questionnaire);
    Long getMainQuestionnaireId () ;

    void  setMainQuestionnaireId(Long id);
}
