package com.example.todoscrud.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.todoscrud.model.Todo;
import com.example.todoscrud.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional
    public Todo updateTodo(Long id, String title, String description) {
        Todo chosenTodo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exist"));
        if (title != null && title.length() > 0 && !Objects.equals(title, chosenTodo.getTitle())) {
            chosenTodo.setTitle(title);
        }
        if (description != null && description.length() > 0
                && !Objects.equals(description, chosenTodo.getDescription())) {
            chosenTodo.setDescription(description);
        }
        todoRepository.save(chosenTodo);
        return chosenTodo;
    }

    public List<Todo> changeTodoDone(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> {
            return new IllegalStateException("todo doesn't exist with this id.");
        });

        boolean done = todo.getDone();
        todo.setDone(!done);
        todoRepository.save(todo);
        return todoRepository.findAll();

    }

    public Todo getTodoById(Long id) {
        boolean TodoExist = todoRepository.existsById(id);
        if (TodoExist) {
            return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("todo not working"));
        } else {
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
    }

}
