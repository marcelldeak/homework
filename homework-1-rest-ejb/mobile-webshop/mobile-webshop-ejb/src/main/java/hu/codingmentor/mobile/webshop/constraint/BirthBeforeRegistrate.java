package hu.codingmentor.mobile.webshop.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthBeforeRegistrateValidator.class)
public @interface BirthBeforeRegistrate {

    String message() default "{BirthBeforeRegistrate.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
