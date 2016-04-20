package hu.codingmentor.mobile.webshop.interceptor;

import java.util.Optional;
import java.util.Set;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import hu.codingmentor.mobile.webshop.annotation.Validable;
import hu.codingmentor.mobile.webshop.qualifier.LoggerQualifier;
import hu.codingmentor.mobile.webshop.qualifier.ValidatorQualifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

@Interceptor
@Validate
public class ValidationInterceptor {

    @Inject
    @ValidatorQualifier
    private Validator validator;

    @Inject
    @LoggerQualifier
    private Logger logger;

    @AroundInvoke
    public Object invoke(InvocationContext ic) throws Exception {
        for (Object o : ic.getParameters()) {
            if (o.getClass().isAnnotationPresent(Validable.class)) {
                Set<ConstraintViolation<Object>> violations = validator.validate(o);
                StringBuilder stringBuilder = new StringBuilder(0);
                Optional<String> errMessage = Optional.empty();
                for (ConstraintViolation<Object> c : violations) {
                    stringBuilder.append("Validation error: ").append(c.getMessage())
                            .append(", invalid value: ").append(c.getInvalidValue());
                    errMessage = Optional.of(stringBuilder.toString());
                }
                if (errMessage.isPresent()) {
                    logger.log(Level.SEVERE, errMessage.get());
                    throw new ValidationException(errMessage.get());
                }
            }
        }
        return ic.proceed();
    }
}
