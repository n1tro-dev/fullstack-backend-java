package org.example.springlab.lab4javaspring.service;

import org.example.springlab.lab4javaspring.entity.Group;
import org.example.springlab.lab4javaspring.entity.Student;
import org.example.springlab.lab4javaspring.repository.GroupRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepo groupRepo;

    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public List<Group> getAllGroups() {
        return groupRepo.findAll();
    }

    public Optional<Group> getGroupById(Long id) {
        return groupRepo.findById(id);
    }

    public Group saveGroup(Group group) {
        if (group.getStudents() != null) {
            for (Student student : group.getStudents()) {
                student.setGroup(group);
            }
        }
        return groupRepo.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepo.deleteById(id);
    }
}