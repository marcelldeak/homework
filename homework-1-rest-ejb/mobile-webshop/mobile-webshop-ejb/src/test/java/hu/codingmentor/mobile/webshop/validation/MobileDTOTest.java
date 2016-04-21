package hu.codingmentor.mobile.webshop.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.Before;
import hu.codingmentor.mobile.webshop.dto.MobileDTO;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Test;

public class MobileDTOTest {

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
    public void mobilePositiveTest() {
        MobileDTO mobile = new MobileDTO("Xperia L", "Sony", 40, 8);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void idNegativeTest(){
        MobileDTO mobile = new MobileDTO("Xperia L", "Sony", 40, 8);
        mobile.setId("invalid id");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals("invalid id", violations.iterator().next().getInvalidValue());
    }
    
    @Test
    public void typeNegativeTest() {
        MobileDTO mobile = new MobileDTO("L", "Sony", 40, 8);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals("L", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void manufacturerNegativeTest() {
        MobileDTO mobile = new MobileDTO("Xperia L", "S", 40, 8);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals("S", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void pieceNegativeTest() {
        MobileDTO mobile = new MobileDTO("Xperia L", "Sony", 0, 8);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void priceNegativeTest() {
        MobileDTO mobile = new MobileDTO("Xperia L", "Sony", 40, -1);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(-1, violations.iterator().next().getInvalidValue());
    }
}
