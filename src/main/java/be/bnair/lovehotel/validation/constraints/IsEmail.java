package be.bnair.lovehotel.validation.constraints;

import be.bnair.lovehotel.validation.validators.IsEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsEmailValidator.class)
public @interface IsEmail {

    String message() default "The email you entered is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
