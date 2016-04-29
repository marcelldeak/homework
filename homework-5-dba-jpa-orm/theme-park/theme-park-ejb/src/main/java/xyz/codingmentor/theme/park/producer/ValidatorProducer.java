package xyz.codingmentor.theme.park.producer;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import xyz.codingmentor.theme.park.qualifier.ValidatorQualifier;

public class ValidatorProducer {

    @Produces
    @ValidatorQualifier
    public Validator produceValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}
