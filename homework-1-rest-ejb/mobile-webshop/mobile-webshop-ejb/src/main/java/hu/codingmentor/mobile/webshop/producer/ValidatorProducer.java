package hu.codingmentor.mobile.webshop.producer;

import hu.codingmentor.mobile.webshop.qualifier.ValidatorQualifier;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorProducer {

    @Produces
    @ValidatorQualifier
    public Validator produceValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}
