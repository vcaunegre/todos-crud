package com.example.todoscrud;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todoscrud.controller.TodoController;

@SpringBootTest()
class TodosCrudApplicationTests {

	@Autowired
	private TodoController todoController;

	@Test
	void contextLoads() {
		assertThat(todoController).isNotNull();
		assertThat(1 + 1).isEqualTo(2);
	}

}
