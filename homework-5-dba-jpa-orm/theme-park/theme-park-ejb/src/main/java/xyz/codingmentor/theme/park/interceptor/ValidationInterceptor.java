package xyz.codingmentor.theme.park.interceptor;

import java.util.Optional;
import java.util.Set;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import javax.inject.Inject;
import javax.validation.ValidationException;
import xyz.codingmentor.theme.park.annotation.Validable;
import xyz.codingmentor.theme.park.qualifier.ValidatorQualifier;

@Interceptor
@Validate
public class ValidationInterceptor {

    @Inject
    @ValidatorQualifier
    private Validator validator;

    @AroundInvoke
    public Object invoke(InvocationContext ic) throws Exception {
        for (Object o : ic.getParameters()) {
            if (o.getClass().isAnnotationPresent(Validable.class)) {
                Set<ConstraintViolation<Object>> violations = validator.validate(o);
                Optional<String> errMessage = generateErrorMessage(violations);
                if (errMessage.isPresent()) {
                    throw new ValidationException(errMessage.get());
                }
            }
        }
        return ic.proceed();
    }
    
    private static Optional<String> generateErrorMessage(Set<ConstraintViolation<Object>> violations){
        StringBuilder stringBuilder = new StringBuilder();
        Optional<String> errMessage = Optional.empty();
        for (ConstraintViolation<Object> c : violations) {
            stringBuilder.append("Validation error: ").append(c.getMessage())
                    .append(", invalid value: ").append(c.getInvalidValue()).append(" ");
            errMessage = Optional.of(stringBuilder.toString());
        }
        return errMessage;
    }
}
