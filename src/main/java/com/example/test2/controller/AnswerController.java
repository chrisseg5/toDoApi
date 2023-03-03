package com.example.test2.controller;

import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Answer;
import com.example.test2.repository.AnswerRepository;
import com.example.test2.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/api")
@RestController
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * Ανάκτηση απαντήσεων απο το id της ερώτησης
     */
    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<Answer>> getAllAnswersByQuestionId(@PathVariable(value = "questionId") Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("question","Id",questionId);
        }

        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }


    /**
     * Ανάκτηση απάντησης απο το id της
     */
    @GetMapping("/answers/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable(value = "id") Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("question","Id",id));

        return new ResponseEntity<>(answer  , HttpStatus.OK);
    }


    /**
     * Δημιουργία  απάντησης μεσω id της ερώτησης
     */
    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<Answer> createAnswer(@PathVariable(value = "questionId") Long questionId,
                                                 @RequestBody Answer answerRequest) {
        Answer comment = questionRepository.findById(questionId).map(t -> {
            answerRequest.setQuestion(t);
            return answerRepository.save(answerRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("question","Id",questionId));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


    /**
     *Διαγραφή απάντησης μεσω id
     */
    @DeleteMapping("/questions/answers/{id}")
    public ResponseEntity<HttpStatus> deleteAnswer(@PathVariable("id") long id) {
        answerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     *Ενημέρωση απάντησης μεσω id
     */
    @PutMapping("/answer/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable("id") long id, @RequestBody Answer answerRequest) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("answer","Id",id));

        answer.setQuestion(answerRequest.getQuestion());

        return new ResponseEntity<>(answerRepository.save(answer), HttpStatus.OK);
    }





}
