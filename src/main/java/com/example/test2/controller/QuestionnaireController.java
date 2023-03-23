package com.example.test2.controller;

import com.example.test2.model.Questionnaire;

import com.example.test2.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class QuestionnaireController {
    @Autowired
    QuestionnaireRepository questionnaireRepository;


    // -------------- Save a Questionnaire ---------------//
    @PostMapping("/questionnaires")
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        Questionnaire _questionnaire = questionnaireRepository.save(
                new Questionnaire(questionnaire.getName()));
        return new ResponseEntity<>( _questionnaire, HttpStatus.CREATED);

    }

    // ----------------Get all Questionnaires -------------//

    @GetMapping("/all/questionnaires")
    public ResponseEntity<List<Questionnaire>> getAllQuestionnaires(@RequestParam(required = false) String name) {
        List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();

        if (name == null)
            questionnaireRepository.findAll().forEach(questionnaires::add);
        else if (questionnaires.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questionnaires, HttpStatus.OK);
    }
}
