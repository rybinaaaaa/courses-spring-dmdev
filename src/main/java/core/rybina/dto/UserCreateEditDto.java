package core.rybina.dto;

import core.rybina.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
    LocalDate birthdate;
    Role role;
    Integer companyId;
}
