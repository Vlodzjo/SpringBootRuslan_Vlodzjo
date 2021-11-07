package validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

public interface CustomValidator {

    Validator getValidator();

    default void validate(Object dto) {
        final Set<ConstraintViolation<Object>> validate = getValidator().validate(dto);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }
}
