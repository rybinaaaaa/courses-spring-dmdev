package core.rybina.dto;

import core.rybina.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    String firstname;
    String lastname;
    LocalDate birthdate;
    Role role;
    CompanyReadDto company;
    String image;
}
