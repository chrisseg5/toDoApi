package com.example.test2.controller;

import com.example.test2.dto.QuenstionnaireIndexDto;
import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Answer;
import com.example.test2.model.Question;
import com.example.test2.model.Questionnaire;

import com.example.test2.repository.QuestionRepository;
import com.example.test2.repository.QuestionnaireRepository;
import com.example.test2.service.QuestionService;
import com.example.test2.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class QuestionnaireController {
    @Autowired
    QuestionnaireRepository questionnaireRepository;
    @Autowired
    QuestionnaireService questionnaireService;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionService questionService;


    // -------------- Save a Questionnaire ---------------//
    @PostMapping(value = "/questionnaire", consumes = {MediaType.ALL_VALUE})
    @ResponseBody
    public Questionnaire createQuestionnaire(@RequestBody @Valid Questionnaire questionnaire) {
        Questionnaire _questionnaire = questionnaireService.createQuestionnaire(questionnaire);
        return _questionnaire;

    }

    // ----------------Get all Questionnaires -------------//

    @GetMapping(value = "/all/questionnaires", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<QuenstionnaireIndexDto> allQuestionnaire() {
        return questionnaireService.allQuestionnaires();
    }


    // ----------------Get Questionnaire with Id -------------//
    @GetMapping("/questionnaires")
    public ResponseEntity<Questionnaire> getQuestionnaireById(@RequestParam long id) {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(id);
        return new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }

    // ----------------Update Questionnaire -------------------//
    @PutMapping("/update/questionnaires/{id}")
    public ResponseEntity<Questionnaire> updateQuestionnaire(@PathVariable("id") long id, @RequestBody Questionnaire questionnaire) {
        Questionnaire _questionnaire = questionnaireService.updateQuestionnaire(id);
        _questionnaire.setName(questionnaire.getName());
        _questionnaire.setGrading(questionnaire.getGrading());
        _questionnaire.setQuestionList(questionnaire.getQuestionList());
        return new ResponseEntity<>(questionnaireRepository.save(_questionnaire), HttpStatus.OK);
    }


    // ----------------Delete Questionnaire -------------------//
    @DeleteMapping("/delete/questionnaire/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionnaire(@PathVariable("id") long id) {
        questionnaireRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/new/q")
    public ResponseEntity<?> saveQ(@RequestBody Questionnaire questionnaire) {
        return new ResponseEntity<>(questionnaireService.saveQ(questionnaire), HttpStatus.CREATED);
    }

// -------------Add a existing question to a questionnaire ---------------//

    @PostMapping("/{questionnaireId}/addQuestion/{questionId}")
    public ResponseEntity<String> addQuestionToQuestionnaire(@PathVariable("questionnaireId") Long questionnaireId, @PathVariable("questionId") Long questionId) {

        try {
            // Retrieve the questionnaire and question objects
            Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
            Question question = questionService.getQuestionById(questionId);

            // Check if the question already exists in the questionnaire
            if (questionnaire.getQuestionList().contains(question)) {
                return ResponseEntity.badRequest().body("Question already exists in the questionnaire.");
            }

            // Add the question to the questionnaire's list of questions
            questionnaire.getQuestionList().add(question);

            // Save the updated questionnaire
            questionnaireService.createQuestionnaire(questionnaire);

            return ResponseEntity.ok("Question added to questionnaire successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add question to questionnaire.");
        }
    }
// -------------Add  existing questions to a questionnaire ---------------//

    @PostMapping("/questionnaire/{questionnaireId}/questions")
    public ResponseEntity<Questionnaire> addQuestionsToQuestionnaire(@PathVariable Long questionnaireId, @RequestBody List<Long> questionIds) {
        // Retrieve the questionnaire from the service
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        if (questionnaire == null) {
            return ResponseEntity.notFound().build();
        }

        // Retrieve the questions from the service
        List<Question> questions = questionService.getQuestionsByIds(questionIds);
        if (questions == null || questions.isEmpty()) {

        }

        // Append the new questions to the existing questions in the questionnaire
        List<Question> existingQuestions = questionnaire.getQuestionList();
        existingQuestions.addAll(questions);

        // Save the updated questionnaire
        Questionnaire updatedQuestionnaire = questionnaireService.createQuestionnaire(questionnaire);

        return new ResponseEntity<>(questionnaire, HttpStatus.CREATED);
    }







}