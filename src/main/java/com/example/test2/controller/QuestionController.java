package com.example.test2.controller;


import com.example.test2.dto.index.QuestionIndexDto;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Question;
import com.example.test2.model.Questionnaire;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.repository.QuestionnaireRepository;
import com.example.test2.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RequestMapping("/api")
@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@Validated
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;



    /**
     * Ανάκτηση ερώτησης απο το id του ερωτηματολογιου
     */
    @GetMapping("/questionnaires/questions/{id}")
    public ResponseEntity<List<Question>> getAllQuestionsByQuestionnaireId(@PathVariable(value = "id") Long id) {
        if (!questionnaireRepository.existsById(id)) {
            throw new ResourceNotFoundException("questionnaire", "id", id);
        }
        List<Question> questions = questionRepository.findQuestionsByQuestionnairesId(id);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * Ανάκτηση ερώτησης απο το id της με παράμετρο
     */
    @GetMapping("/question")
    public ResponseEntity<Question> getQuestionById(@RequestParam long id) {
        Question question = questionService.getQuestionById(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    /**
     * Δημιουργία ερώτησης
     */
    @PostMapping(value = "/question/saveQuestion", consumes = {MediaType.ALL_VALUE})
    @ResponseBody

    public Question saveQuestion(@RequestBody @Valid Question question) {
        Question questionResponse = questionService.saveQuestion(question);
        return questionResponse;
    }

    /**
     * Διαγραφή ερώτησης μέσω id
     */
    @DeleteMapping("/question/delete")
    public ResponseEntity<HttpStatus> deleteQuestion(@RequestParam long id) {
        questionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ενημέρωση ερώτησης μεσω id
     */
    @PutMapping("/question/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") long id, @RequestBody Question question) {
        Question _question = questionService.updateQuestion(id);

        _question.setQuestionText(question.getQuestionText());


        return new ResponseEntity<>(questionRepository.save(_question), HttpStatus.OK);
    }

    /**
     * Ανάκτηση όλων των ερωτήσεων
     */
    @GetMapping(value = "/questions", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<QuestionIndexDto> allQuestions() {
        return questionService.allQuestions();
    }

    /**
     * Διαγραφή ερώτησης μονο απο ερωτηματολόγιο
     */
    @DeleteMapping("/forms/{formsId}/questions/{questionsId}")
    public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "formsId") Long formsId, @PathVariable(value = "questionsId") Long questionsId) {
    Questionnaire questionnaire = questionnaireRepository.findById(formsId)
                .orElseThrow(() -> new ResourceNotFoundException("questionnaire","id" ,formsId));

        questionnaire.remove(questionsId);
        questionnaireRepository.save(questionnaire);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}


