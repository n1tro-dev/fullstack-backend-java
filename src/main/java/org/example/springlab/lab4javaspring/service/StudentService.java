package org.example.springlab.lab4javaspring.service;

import org.example.springlab.lab4javaspring.entity.Group;
import org.example.springlab.lab4javaspring.entity.Student;
import org.example.springlab.lab4javaspring.repository.GroupRepo;
import org.example.springlab.lab4javaspring.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;

    public StudentService(StudentRepo studentRepo, GroupRepo groupRepo) {
        this.studentRepo = studentRepo;
        this.groupRepo = groupRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }
    

    public Student assignGroup(Long studentId, Long groupId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        student.setGroup(group);
        return studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}