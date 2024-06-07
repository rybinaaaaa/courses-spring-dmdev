package core.rybina.http.rest;

import core.rybina.database.service.UserService;
import core.rybina.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    //    @GetMapping(получаем: consumes = MediaType.APPLICATION_JSON_VALUE, отправляем: produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public PageResponse<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        Page<UserReadDto> users = userService.findAll(userFilter, pageable);
        return PageResponse.of(users);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@RequestBody @Validated UserCreateEditDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id, @RequestBody @Validated UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
