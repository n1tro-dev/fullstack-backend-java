package org.example.springlab.lab4javaspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.springlab.lab4javaspring.entity.Group;
import org.example.springlab.lab4javaspring.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@Tag(name = "Group Controller", description = "Управление группами")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех групп")
    public List<Group> getAll() {
        return groupService.getAllGroups();
    }

    @PostMapping
    @Operation(summary = "Создать группу")
    public Group create(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить группу по id")
    public ResponseEntity<Group> getById(@PathVariable Long id) {
        return groupService.getGroupById(id)
                .map(group -> ResponseEntity.ok().body(group))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить группу")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}