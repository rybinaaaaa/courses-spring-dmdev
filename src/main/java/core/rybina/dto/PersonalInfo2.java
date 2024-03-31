package core.rybina.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PersonalInfo2 {

    String getFirstname();

    String getLastname();

    String getBirthDate();

//    обращаемся к объекту определенной проекции через target
    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
