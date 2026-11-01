package org.example.springlab.lab4javaspring.repository;

import org.example.springlab.lab4javaspring.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
    List<Group> findByName(String name);
}