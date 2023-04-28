package com.example.test2.controller;

import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Gradingg;
import com.example.test2.model.Question;
import com.example.test2.model.Questionnaire;
import com.example.test2.repository.GradinggRepository;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RequestMapping("/api")
@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@Validated
public class GradinggController {
    @Autowired
    private GradinggRepository gradinggRepository;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @PostMapping("/questionnaireee/{questionnaireId}/questionnn/{questionId}")
    public ResponseEntity<Gradingg> addQuestionToQuestionnaire(@PathVariable Long questionnaireId,
                                                               @PathVariable Long questionId,
                                                               @RequestBody Gradingg gradingg) {
        Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Questionnaire", "id", questionnaireId));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));

        // Set the entities in the join table entity
        gradingg.setQuestion(question);
        gradingg.setQuestionnaire(questionnaire);

        // Save the join table entity with the grading
        Gradingg newQuestionnaireQuestion = gradinggRepository.save(gradingg);

        return null ;
    }
}
