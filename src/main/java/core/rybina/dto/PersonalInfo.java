package core.rybina.dto;

import java.time.LocalDate;

public record PersonalInfo(String firstname, String lastname, LocalDate birthdate) {
}
