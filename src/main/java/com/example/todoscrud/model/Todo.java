package com.example.todoscrud.model;


import jakarta.persistence.*;

@Entity
@Table
public class Todo {
    @Id
    @SequenceGenerator(name = "todo_sequence", allocationSize = 1, sequenceName = "todo_sequence")
    @GeneratedValue(generator = "todo_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private boolean done;

    public Todo() {
    }

    public Todo(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
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

    public boolean isDone() {
        return this.done;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
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
