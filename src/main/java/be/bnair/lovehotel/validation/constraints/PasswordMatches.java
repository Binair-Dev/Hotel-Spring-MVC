package be.bnair.lovehotel.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import be.bnair.lovehotel.validation.validators.PasswordConfirmationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmationValidator.class)
public @interface PasswordMatches {
    String message() default "Password not matching";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}