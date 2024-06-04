package core.rybina.dto;

import core.rybina.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@FieldNameConstants
@Value
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthdate;
    Role role;
    Integer companyId;
}
