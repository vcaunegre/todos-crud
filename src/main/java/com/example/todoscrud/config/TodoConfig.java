package com.example.todoscrud.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.todoscrud.model.Todo;
import com.example.todoscrud.repository.TodoRepository;

@Configuration
public class TodoConfig {

    @Bean
    CommandLineRunner commandLineRunner(TodoRepository todoRepository) {
        return args -> {
            Todo todo1 = new Todo(
                    "Todo1",
                    "This is a todo");
            Todo todo2 = new Todo(
                    "Todo2",
                    "This is another todo");
            todoRepository.saveAll(
                    List.of(todo1, todo2));
        };
    }
}
