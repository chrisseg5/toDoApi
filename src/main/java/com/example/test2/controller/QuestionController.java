package com.example.test2.controller;
import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Question;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@Controller
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;

    /**
     * Ανάκτηση ερώτησης απο το id της
     */
    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("question","Id",id));

        return new ResponseEntity<>(question, HttpStatus.OK);

    }
    /**
     *Δημιουργία ερώτησης
     */
    @PostMapping(value = "/question/saveQuestion")
    @ResponseBody
    public Question saveQuestion(@RequestBody Question question){
        Question questionResponse = questionService.saveQuestion(question);
        return  questionResponse;
    }
    /**
     *Διαγραφή ερώτησης μέσω id
     */
    @DeleteMapping("/question/delete/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id") long id) {
        questionRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     *Ενημέρωση ερώτησης μεσω id
     */
    @PutMapping("/question/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") long id, @RequestBody Question question) {
        Question _question = questionRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("question","Id",id));

        _question.setQuestionText(question.getQuestionText());


        return new ResponseEntity<>(questionRepository.save(_question), HttpStatus.OK);
    }
    /**
     * Ανάκτηση όλων των ερωτήσεων
     */
    @GetMapping("/questions")
    @ResponseBody
    public List<Question> getAllQuestions() {
        List<Question> questionResponse = (List<Question>) questionRepository.findAll();
        return questionResponse;
    }


}
