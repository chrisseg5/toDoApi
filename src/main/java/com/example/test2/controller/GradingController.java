package com.example.test2.controller;

import com.example.test2.model.Grading;
import com.example.test2.model.Question;
import com.example.test2.model.Questionnaire;
import com.example.test2.repository.GradingRepository;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.repository.QuestionnaireRepository;
import com.example.test2.service.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.example.test2.exception.ResourceNotFoundException;

import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200/")

public class GradingController {
    @Autowired
    private GradingService gradingService;



    @Autowired
    private GradingRepository gradingRepository;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionRepository questionRepository;


    // -------------Add grade-------------------------------------------//

    @PostMapping("/new/questionnaires/{questionnaireId}/questions/{questionId}")
    public ResponseEntity<Grading> addQuestionToQuestionnaire(@PathVariable Long questionnaireId,
                                                              @PathVariable Long questionId,
                                                              @RequestBody Grading grading) {
        Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Questionnaire", "id", questionnaireId));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));

        grading.setQuestion(question);
        grading.setQuestionnaire(questionnaire);

        Grading newQuestionnaireQuestion = gradingRepository.save(grading);

        return null ;
    }



    @GetMapping("/get/{questionnaireId}/get/{questionId}")
    public ResponseEntity<Grading> getGradingByQuestionnaireAndQuestionIds(
            @PathVariable("questionnaireId") Long questionnaireId,
            @PathVariable("questionId") Long questionId) {

        Optional<Grading> optionalGrading = gradingRepository.findByQuestionnaireIdAndQuestionId(questionnaireId, questionId);

        if (optionalGrading.isPresent()) {
            return ResponseEntity.ok(optionalGrading.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("grade/{id}")
    public ResponseEntity<Grading> getGradingById(@PathVariable(value = "id") Long gradingId) {
        Optional<Grading> grading = gradingRepository.findById(gradingId);
        if (!grading.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(grading.get());
    }


}
