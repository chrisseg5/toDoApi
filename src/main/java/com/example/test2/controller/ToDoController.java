package com.example.test2.controller;

import com.example.test2.model.ToDo;
import com.example.test2.repository.ToDoRepository;
import com.example.test2.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class ToDoController {
    @Autowired
    ToDoService toDoService;
    @Autowired
    ToDoRepository toDoRepository;

    @PostMapping(value = "/add/toDo" ,consumes = {MediaType.ALL_VALUE})
    public ToDo addToDo(@RequestBody @Valid ToDo toDo) {
        ToDo _toDo =toDoService.saveToDo(toDo);
        return _toDo;
    }

    @GetMapping(value = "/all/todos", produces = "application/json;charset=UTF-8")
    public List<ToDo> getAllTodos() {
        return toDoService.getAllTodos();
    }

    @DeleteMapping("/todo/delete/{id}")
    public ResponseEntity<HttpStatus> deleteToDo(@PathVariable long id) {
        toDoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/todo/updateStatus/{id}")
    public ResponseEntity<String> updateToDoStatus(@PathVariable long id, @RequestParam boolean done) {
        toDoService.updateToDoStatus(id, done);
        return new ResponseEntity<>("ToDo status updated successfully", HttpStatus.OK);
    }
}
