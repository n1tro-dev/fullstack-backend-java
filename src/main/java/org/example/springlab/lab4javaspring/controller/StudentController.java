package org.example.springlab.lab4javaspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.springlab.lab4javaspring.entity.Student;
import org.example.springlab.lab4javaspring.repository.StudentRepo;
import org.example.springlab.lab4javaspring.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Controller", description = "Управление студентами")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех студентов")
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @PostMapping
    @Operation(summary = "Создать студента")
    public Student create(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить студента по ID")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{studentId}/group/{groupId}")
    @Operation(summary = "Привязать студента к группе")
    public ResponseEntity<Student> assignGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        return ResponseEntity.ok(studentService.assignGroup(studentId, groupId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить студента")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}