package com.example.test2.service;

import com.example.test2.model.ToDo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public interface ToDoService {
    ToDo saveToDo (ToDo toDo);
    List<ToDo> getAllTodos();
    void updateToDoStatus(long id, boolean done);

}
