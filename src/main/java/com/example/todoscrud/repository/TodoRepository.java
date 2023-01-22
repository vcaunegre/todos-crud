package com.example.todoscrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoscrud.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // @Query("SELECT * FROM TODO WHERE ID<?1")
    // Optional<List<Todo>> findTodoIdInferior(int id);
}
