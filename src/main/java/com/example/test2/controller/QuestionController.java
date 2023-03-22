package com.example.test2.controller;

import com.example.test2.args.QuestionArgs;
import com.example.test2.dto.QuestionIndexDto;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Question;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.QuestionService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    /**
     * Ανάκτηση ερώτησης απο το id της
     */
//    @GetMapping("/question/{id}")
//    public ResponseEntity<Question> getQuestionById(@PathVariable("id") long id) {
//        Question question = questionRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("question","Id",id));
//
//        return new ResponseEntity<>(question, HttpStatus.OK);
//
//    }

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


//
//    @PostMapping(value = "/index", produces = "application/json;charset=UTF-8")
//    public Page<QuestionIndexDto> campaignIndex(Pageable pageable, @RequestBody QuestionArgs args) {
//        return questionService.questionIndex(args, pageable);
//    }
//


}
