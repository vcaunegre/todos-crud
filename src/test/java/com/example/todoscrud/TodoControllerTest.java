package com.example.todoscrud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.example.todoscrud.model.Todo;

public class TodoControllerTest extends AbstractTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void getTodosList() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/todos").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Todo[] todoList = super.mapFromJson(content, Todo[].class);
        assertTrue(todoList.length > 0);
        assertEquals(todoList[0].getTitle(), "A Todo ");
    }

    @Test
    void shouldCreateTodo() throws Exception {
        Todo todo = new Todo("todo 1", "a todo", false);

        MvcResult mvcResult = mvc
                .perform(post("/api/todos").accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON).content(super.mapToJson(todo)))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Todo todoResult = super.mapFromJson(content, Todo.class);

        assertEquals(todo.getTitle(), todoResult.getTitle());
    }
}