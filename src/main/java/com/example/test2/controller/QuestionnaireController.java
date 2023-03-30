package com.example.test2.controller;

import com.example.test2.dto.QuenstionnaireIndexDto;
import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Question;
import com.example.test2.model.Questionnaire;

import com.example.test2.repository.QuestionnaireRepository;
import com.example.test2.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class QuestionnaireController {
    @Autowired
    QuestionnaireRepository questionnaireRepository;
    @Autowired
    QuestionnaireService questionnaireService;


    // -------------- Save a Questionnaire ---------------//
    @PostMapping(value = "/questionnaires", consumes = {MediaType.ALL_VALUE})
    @ResponseBody
    public Questionnaire createQuestionnaire(@RequestBody @Valid  Questionnaire questionnaire) {
        Questionnaire _questionnaire = questionnaireService.createQuestionnaire(questionnaire);
        return _questionnaire ;

    }

    // ----------------Get all Questionnaires -------------//

    @GetMapping(value = "/all/questionnaires", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<QuenstionnaireIndexDto> allQuestionnaire() {
        return questionnaireService.allQuestionnaires() ;
    }



    // ----------------Get Questionnaire with Id -------------//
    @GetMapping("/questionnaires")
    public ResponseEntity<Questionnaire> getQuestionnaireById(@RequestParam long id) {
        Questionnaire questionnaire =questionnaireService.getQuestionnaireById(id);
        return new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }

    // ----------------Update Questionnaire -------------------//
    @PutMapping("/update/questionnaires/{id}")
    public ResponseEntity<Questionnaire> updateQuestionnaire(@PathVariable("id") long id, @RequestBody Questionnaire questionnaire) {
        Questionnaire _questionnaire = questionnaireService.updateQuestionnaire(id);
        _questionnaire.setName(questionnaire.getName());
        return new ResponseEntity<>(questionnaireRepository.save(_questionnaire), HttpStatus.OK);
    }


    // ----------------Delete Questionnaire -------------------//
    @DeleteMapping("/delete/questionnaire/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionnaire(@PathVariable("id") long id) {
        questionnaireRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
