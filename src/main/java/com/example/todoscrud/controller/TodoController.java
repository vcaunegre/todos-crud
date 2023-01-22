package com.example.todoscrud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoscrud.model.Todo;
import com.example.todoscrud.service.TodoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    @GetMapping(path = "/{id}")
    public Todo getTodoById(@PathVariable("id") Long id) {
        return todoService.getTodoById(id);
    }

    @PutMapping(path = "/{id}")
    public List<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo newTodo) {
        todoService.updateTodo(id, newTodo.getTitle(), newTodo.getDescription());
        return todoService.getTodos();
    }

    @PutMapping(path = "/done/{id}")
    public List<Todo> changeTodoState(@PathVariable("id") Long id, @RequestBody Todo newTodo) {
        return todoService.changeTodoDone(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().body(todoService.getTodos());
    }
}
