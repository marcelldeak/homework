
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
import hu.codingmentor.mobile.webshop.qualifier.ValidatorQualifier;
import javax.inject.Inject;

@Interceptor
@Validate
public class ValidationInterceptor{
    
    @Inject @ValidatorQualifier
    private Validator validator;
    
    @AroundInvoke
    public Object invoke(InvocationContext ic) throws Exception{
        for(Object o : ic.getParameters()){
            if(o.getClass().isAnnotationPresent(Validable.class)){
                Set<ConstraintViolation<Object>> violations = validator.validate(o);
                StringBuilder stringBuilder = new StringBuilder(0);
                Optional<String> errMessage = Optional.empty();
                for(ConstraintViolation<Object> c : violations){
                    stringBuilder.append("Validation error: ").append(c.getMessage())
                            .append(", invalid value: ").append(c.getInvalidValue());
                    errMessage = Optional.of(stringBuilder.toString());
                }
                if(errMessage.isPresent())
                    throw new ValidationException(errMessage.toString());
            }
        }
        return ic.proceed();
    }
}
