package org.example.springlab.lab4javaspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.springlab.lab4javaspring.entity.Profile;
import org.example.springlab.lab4javaspring.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/profiles")
@Tag(name = "Profile Controller", description = "Управление профилями")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить профиль по ID")
    public ResponseEntity<Profile> getById(@PathVariable Long id){
        return profileService.getProfileById(id)
                .map(profile -> ResponseEntity.ok().body(profile))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/student/{studentId}")
    @Operation(summary = "Создать и привязать профиль к студенту")
    public ResponseEntity<Profile> createProfile(@PathVariable Long studentId, @RequestBody Profile profile){
        Profile createdProfile = profileService.createProfileForStudent(studentId, profile);
        return ResponseEntity.ok(createdProfile);
    }
}
