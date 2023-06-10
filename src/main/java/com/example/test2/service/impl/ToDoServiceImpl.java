package com.example.test2.service.impl;

import com.example.test2.model.ToDo;
import com.example.test2.repository.ToDoRepository;
import com.example.test2.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;
    @Override
    public ToDo saveToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }
    @Override
    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    @Override
    public void updateToDoStatus(long id, boolean done) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()) {
            ToDo toDo = optionalToDo.get();
            toDo.setDone(done);
            toDoRepository.save(toDo);
        }
    }
}
