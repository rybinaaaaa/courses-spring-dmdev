package core.rybina.database.integration.repository;

import core.rybina.database.entity.Role;
import core.rybina.database.entity.User;
import core.rybina.database.integration.annotation.IT;
import core.rybina.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkPageable() {
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        List<User> result = userRepository.findAllBy(pageable);
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    void checkFirstTop() {
        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));

        List<User> allUsers1 = userRepository.findTop3ByBirthdateBeforeOrderByBirthdateDesc(LocalDate.now());
        Assertions.assertThat(allUsers1).hasSize(3);

        List<User> allUsers2 = userRepository.findTop3ByBirthdateBefore(LocalDate.now(), Sort.sort(User.class).by(User::getBirthdate).descending());
//        List<User> allUsers3 = userRepository.findTop3ByBirthdateBefore(LocalDate.now(), Sort.by("birthdate").descending());
        Assertions.assertThat(allUsers2).hasSize(3);

        assertEquals(allUsers1.get(0), allUsers2.get(0));
    }

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