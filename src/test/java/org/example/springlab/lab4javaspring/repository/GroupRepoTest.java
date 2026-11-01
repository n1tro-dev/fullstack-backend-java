package org.example.springlab.lab4javaspring.repository;

import jakarta.transaction.Transactional;
import org.example.springlab.lab4javaspring.entity.Group;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class GroupRepoTest {

    @Autowired
    private GroupRepo groupRepo;

    @Test
    @DisplayName("Тест сохранения и поиска группы по имени")
    void saveGroup() {
        groupRepo.save(new Group("SE-2026"));
        assertThat(groupRepo.findByName("SE-2026")).isNotEmpty();
    }

    @Test
    @DisplayName("Тест удаления всех групп из базы данных")
    void deleteGroups() {
        groupRepo.save(new Group("IT-101"));
        groupRepo.deleteAll();
        assertThat(groupRepo.count()).isEqualTo(0);
    }
}