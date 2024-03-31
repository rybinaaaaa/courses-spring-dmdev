package core.rybina.database.integration.repository;

import core.rybina.database.entity.Role;
import core.rybina.database.entity.User;
import core.rybina.database.integration.annotation.IT;
import core.rybina.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkUpdate() {
        User ivan = userRepository.getReferenceById(1L);
        int resCount = userRepository.updateRole(Role.USER, 1L, 5L);

        assertEquals(2, resCount);

        User theSameIvan = userRepository.getReferenceById(1L);
        assertSame(Role.USER, theSameIvan.getRole());
    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        Assertions.assertThat(users).hasSize(3);
        System.out.println(users);
    }
}