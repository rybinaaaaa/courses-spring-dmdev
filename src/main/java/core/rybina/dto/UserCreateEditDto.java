package core.rybina.dto;

import core.rybina.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@FieldNameConstants
@Value
public class UserCreateEditDto {

    @Email
    String username;

    @NotNull
    String firstname;

    @NotNull
    @Size(min = 3, max = 50)
    String lastname;

    @NotNull
    LocalDate birthdate;

    Role role;

    Integer companyId;
}
