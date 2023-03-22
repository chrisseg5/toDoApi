package com.example.test2.controller;

import com.example.test2.exception.ResourceNotFoundException;
import com.example.test2.model.Answer;
import com.example.test2.repository.AnswerRepository;
import com.example.test2.repository.QuestionRepository;
import com.example.test2.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * Ανάκτηση απαντήσεων απο το id της ερώτησης
     */
    @GetMapping("/answers")
    public ResponseEntity<List<Answer>> getAllAnswersByQuestionId(@RequestParam long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("question", "Id", id);
        }
        List<Answer> answers =answerService.getAllAnswersById(id);
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }


    /**
     * Δημιουργία  απάντησης μεσω id της ερώτησης με Request Param
     */
//    @PostMapping("/new/answer")
//    public ResponseEntity<Answer> createAnswer(@RequestParam long id,
//                                               @RequestBody Answer answerRequest) {
//        Answer comment = questionRepository.findById(id).map(t -> {
//            answerRequest.setQuestion(t);
//            return answerRepository.save(answerRequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("question","Id",id));
//
//        return new ResponseEntity<>(comment, HttpStatus.CREATED);
//    }

    /**
     * Δημιουργία  απάντησης μεσω id της ερώτησης
     */
    @PostMapping("/new/answer/{id}")
    public ResponseEntity<Answer> createAnswer(@PathVariable("id") long id, @RequestBody Answer answerRequest) {
        Answer comment = questionRepository.findById(id).map(t -> {
            answerRequest.setQuestion(t);
            return answerRepository.save(answerRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("question", "Id", id));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


    @PostMapping("/register")
    public void saveAnswers(@RequestBody Answer answer) {
        List<Answer> persistedUser = answerRepository.saveAll(List.of(answer));
    }


    /**
     * Ανάκτηση απάντησης απο το id της
     */
//    @GetMapping("/answers/{id}")
//    public ResponseEntity<Answer> getAnswer(@PathVariable(value = "id") Long id) {
//        Answer answer = answerRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("question","Id",id));
//
//        return new ResponseEntity<>(answer  , HttpStatus.OK);
//    }


    /**
     * Διαγραφή απάντησης μεσω id
     */
    @DeleteMapping("/questions/answers/{id}")
    public ResponseEntity<HttpStatus> deleteAnswer(@PathVariable("id") long id) {
        answerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ενημέρωση απάντησης μεσω id
     */
    @PutMapping("/answer/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable("id") long id, @RequestBody Answer answerRequest) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("answer", "Id", id));

        answer.setQuestion(answerRequest.getQuestion());

        return new ResponseEntity<>(answerRepository.save(answer), HttpStatus.OK);
    }


}
