package core.rybina.dto;

import java.time.LocalDate;

public record UserFilter(String firstname, String lastname, LocalDate birthdate) {
}
