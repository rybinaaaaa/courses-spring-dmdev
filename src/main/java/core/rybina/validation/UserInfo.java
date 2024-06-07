package core.rybina.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserInfoValidator.class)
public @interface UserInfo {

    String message() default "Wrong firstname or lastname";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
