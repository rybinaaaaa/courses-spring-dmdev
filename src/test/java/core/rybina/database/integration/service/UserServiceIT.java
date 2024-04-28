package core.rybina.database.integration.service;

import core.rybina.database.entity.Role;
import core.rybina.database.integration.IntegrationTestBase;
import core.rybina.database.service.UserService;
import core.rybina.dto.UserCreateEditDto;
import core.rybina.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private final UserService userService;
    private final Long USER_1 = 1L;
    private final Integer COMPANY_1 = 1;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                "Test",
                LocalDate.now(),
                Role.ADMIN,
                COMPANY_1
        );

        UserReadDto actualResult = userService.create(userCreateEditDto);
        assertEquals(userCreateEditDto.getUsername(), actualResult.getUsername());
        assertEquals(userCreateEditDto.getBirthdate(), actualResult.getBirthdate());
        assertEquals(userCreateEditDto.getCompanyId(), actualResult.getCompany().id());
    }

    @Test
    void update() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                "Test",
                LocalDate.now(),
                Role.ADMIN,
                COMPANY_1
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userCreateEditDto);
        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(user -> assertEquals(userCreateEditDto.getUsername(), user.getUsername()));
    }

//    @Test
//    void delete() {
//    без флаша будет работать неправильно
//       assertFalse(userService.delete(-1233L));
//    }
}
