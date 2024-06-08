package core.rybina.dto;

import core.rybina.database.entity.Role;
import core.rybina.validation.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@FieldNameConstants
@Value
@UserInfo
public class UserCreateEditDto {

    @Email
    String username;

//    @NotNull
    String firstname;

//    @NotNull
//    @Size(min = 3, max = 50)
    String lastname;

    @NotNull
    LocalDate birthdate;

    Role role;

    Integer companyId;

    MultipartFile image;

    @NotBlank
    String rowPassword;
}
