
package hu.codingmentor.mobile.webshop.constraint;

import hu.codingmentor.mobile.webshop.dto.UserDTO;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class BirthBeforeRegistrateValidator implements ConstraintValidator<BirthBeforeRegistrate, UserDTO>{

    @Override
    public void initialize(BirthBeforeRegistrate constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        if(null == user.getDateOfBirth())
            return true;
        return (user.getDateOfBirth().isBefore(user.getRegistrationDate()) && 
                user.getDateOfBirth().isBefore(LocalDate.now()));
    }
}
