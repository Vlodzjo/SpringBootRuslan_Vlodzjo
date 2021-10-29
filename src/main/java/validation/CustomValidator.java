package validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

public interface CustomValidator<T> {

    Validator getValidator();

    default void validate(T dto) {
        final Set<ConstraintViolation<T>> validate = getValidator().validate(dto);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }
}
