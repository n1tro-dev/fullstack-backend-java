package org.example.springlab.lab4javaspring.controller;

import org.example.springlab.lab4javaspring.service.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("401 Unauthorized: Отказ в доступе к списку студентов без авторизации")
    public void testGetAllStudentsUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/api/students"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("200 OK: Успешное получение списка студентов с валидным JWT токеном")
    public void testGetAllStudentsAuthenticated() throws Exception {
        String token = jwtService.getToken("admin");

        this.mockMvc.perform(get("/api/students")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("200 OK: Успешное создание нового студента")
    public void testCreateStudent() throws Exception {
        String token = jwtService.getToken("admin");
        String newStudentJson = "{\"name\":\"Иван Петров\",\"email\":\"ivan@example.com\"}";

        this.mockMvc.perform(post("/api/students")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStudentJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}