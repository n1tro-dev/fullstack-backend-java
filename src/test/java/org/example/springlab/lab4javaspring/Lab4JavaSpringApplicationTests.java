package org.example.springlab.lab4javaspring;

import org.example.springlab.lab4javaspring.controller.StudentController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Lab4JavaSpringApplicationTests {

    @Autowired
    private StudentController studentController;

    @Test
    @DisplayName("Проверка инициализации StudentController в контексте Spring")
    void contextLoads() {
        // Проверяем, что бин контроллера внедрен успешно
        assertThat(studentController).isNotNull();
    }

}
