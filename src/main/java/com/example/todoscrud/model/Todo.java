package com.example.todoscrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Todo {
    @Id
    @SequenceGenerator(name = "todo_sequence", allocationSize = 1, sequenceName = "todo_sequence")
    @GeneratedValue(generator = "todo_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;

    public Todo() {
    }

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Todo id(Long id) {
        setId(id);
        return this;
    }

    public Todo title(String title) {
        setTitle(title);
        return this;
    }

    public Todo description(String description) {
        setDescription(description);
        return this;
    }

}
