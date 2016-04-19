package hu.codingmentor.mobile.webshop.validation;

import hu.codingmentor.mobile.webshop.dto.UserDTO;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Before
    public void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void close() {
        validatorFactory.close();
    }

    @Test
    public void userPositiveTest() {
        UserDTO user = new UserDTO("testuser", "asdFGH123,.", LocalDate.of(1992, 2, 15));
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void usernameNegativeTest() {
        UserDTO user = new UserDTO("tu", "asdFGH123,.", LocalDate.of(1992, 2, 15));
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("tu", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void passwordSizeNegativeTest() {
        UserDTO user = new UserDTO("testuser", "aA1,", LocalDate.of(1992, 2, 15));
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("aA1,", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void passwordPatternNegativeTest() {
        UserDTO user = new UserDTO("testuser", "asdFGH123", LocalDate.of(1992, 2, 15));
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("asdFGH123", violations.iterator().next().getInvalidValue());

        violations.clear();

        user = new UserDTO("testuser", "asdFGH,.", LocalDate.of(1992, 2, 15));
        violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("asdFGH,.", violations.iterator().next().getInvalidValue());

        violations.clear();

        user = new UserDTO("testuser", "asd123,.", LocalDate.of(1992, 2, 15));
        violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("asd123,.", violations.iterator().next().getInvalidValue());

        violations.clear();

        user = new UserDTO("testuser", "FGH123,.", LocalDate.of(1992, 2, 15));
        violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("FGH123,.", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void dateOfBirthNegativeTest() {
        UserDTO user = new UserDTO("testuser", "asdFGH123,.", LocalDate.of(2017, 2, 15));
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Birth date must be earlier than registration date and actual date",
                violations.iterator().next().getMessage());
        Assert.assertEquals(user, violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{BirthBeforeRegistrate.message}", violations.iterator().next().getMessageTemplate());
    }
}
