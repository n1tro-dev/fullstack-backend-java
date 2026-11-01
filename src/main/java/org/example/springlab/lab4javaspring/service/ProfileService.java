package org.example.springlab.lab4javaspring.service;

import org.example.springlab.lab4javaspring.entity.Profile;
import org.example.springlab.lab4javaspring.entity.Student;
import org.example.springlab.lab4javaspring.repository.GroupRepo;
import org.example.springlab.lab4javaspring.repository.ProfileRepo;
import org.example.springlab.lab4javaspring.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepo profileRepo;
    private final StudentRepo studentRepo;

    public ProfileService(ProfileRepo profileRepo, StudentRepo studentRepo) {
        this.profileRepo = profileRepo;
        this.studentRepo = studentRepo;
    }

    public Optional<Profile> getProfileById(Long id){
        return profileRepo.findById(id);
    }

    public Profile createProfileForStudent(Long studentId, Profile profile) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        if (student.getProfile() != null) {
            throw new RuntimeException("У этого студента уже есть профиль!");
        }

        student.setProfile(profile);
        studentRepo.save(student);
        return student.getProfile();
    }
}
