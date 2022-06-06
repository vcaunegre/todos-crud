package com.example.todoscrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoscrud.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
