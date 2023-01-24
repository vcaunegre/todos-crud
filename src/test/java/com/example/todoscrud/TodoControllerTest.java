package com.example.todoscrud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.example.todoscrud.model.Todo;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TodoControllerTest extends AbstractTest {
        @Override
        @BeforeEach
        public void setUp() {
                super.setUp();
        }

        @BeforeEach
        public void doThings() throws JsonProcessingException, Exception {
                Todo todo = new Todo(1L, "todo 1", "a todo", false);
                Todo todo2 = new Todo(2L, "todo 2", "a todo", false);
                mvc
                                .perform(post("/api/todos").accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON).content(super.mapToJson(todo)))
                                .andReturn();
                mvc
                                .perform(post("/api/todos").accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(super.mapToJson(todo2)))
                                .andReturn();
        }

        @Test
        void getTodosList() throws Exception {
                MvcResult mvcResult = mvc.perform(get("/api/todos").accept(MediaType.APPLICATION_JSON_VALUE))
                                .andReturn();

                int status = mvcResult.getResponse().getStatus();
                assertEquals(200, status);
                String content = mvcResult.getResponse().getContentAsString();
                Todo[] todoList = super.mapFromJson(content, Todo[].class);
                for (Todo todo : todoList) {
                        System.out.println(todo.getId());
                }
                assertTrue(todoList.length == 2);
        }

        @Test
        void shouldCreateTodo() throws Exception {
                Todo todo = new Todo(3L, "todo 3", "a todo", false);

                MvcResult mvcResult = mvc
                                .perform(post("/api/todos").accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON).content(super.mapToJson(todo)))
                                .andReturn();

                int status = mvcResult.getResponse().getStatus();
                assertEquals(200, status);

                String content = mvcResult.getResponse().getContentAsString();
                Todo todoResult = super.mapFromJson(content, Todo.class);

                assertEquals(todoResult.getDescription(), todo.getDescription());
                assertEquals(todoResult.getId(), 3L);
        }

        @Test
        void shouldEditTodo() throws Exception {
                Todo todo = new Todo(1L, "Todo edited", "I edited this todo", false);

                String uri = "/api/todos/1";
                MvcResult putTodo = mvc.perform(put(uri).accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON).content(super.mapToJson(todo))).andReturn();

                Todo[] todoResult = super.mapFromJson(putTodo.getResponse().getContentAsString(), Todo[].class);

                assertThat(todoResult[0].getDescription()).isEqualTo(todo.getDescription());
        }

        @Test
        void shouldDeleteTodo() throws Exception {
                String uri = "/api/todos/2";
                MvcResult deleteTodo = mvc
                                .perform(delete(uri).accept(MediaType.APPLICATION_JSON)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andReturn();

                int status = deleteTodo.getResponse().getStatus();

                assertEquals(status, 200);

        }

}