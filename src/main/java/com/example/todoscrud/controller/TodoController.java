package com.example.todoscrud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = { "http://localhost:4200", "https://ngtodolist64.netlify.app" })
@RequestMapping(path = "todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @PostMapping
    public void addTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().body("todo deleted successfully");
    }
}
