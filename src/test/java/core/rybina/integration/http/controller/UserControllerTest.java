package core.rybina.integration.http.controller;

import core.rybina.database.entity.Role;
import core.rybina.database.integration.IntegrationTestBase;
import core.rybina.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static core.rybina.dto.UserCreateEditDto.Fields.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// Важно не использовать IsCollectionWithSize из тестконтейнеров!
//import static org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

//Имитация http запросы
@AutoConfigureMockMvc
@RequiredArgsConstructor
@WithMockUser(username = "test@gmail.com", authorities = {"ADMIN", "USER"})
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

//    @BeforeEach
//    void init() {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//
//        List<GrantedAuthority> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        User user = new User("test@gmail.com", "test", roles);
//
//        TestingAuthenticationToken token = new TestingAuthenticationToken(user, user.getPassword(), roles);
//        context.setAuthentication(token);
//        SecurityContextHolder.setContext(context);
//    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "test@gmail.com")
                        .param(firstname, "Test")
                        .param(lastname, "TestTest")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                        .param(birthdate, "2000-01-01")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }
}