package com.example.todoscrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.todoscrud.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // @Query("SELECT * FROM TODO WHERE ID<?1")
    // Optional<List<Todo>> findTodoIdInferior(int id);
}
