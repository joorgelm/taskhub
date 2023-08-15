package br.com.wk.taskhub.application.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Optional;
import java.util.Set;

public abstract class AbstractIOData {

    public void validarDados() {
        Optional.ofNullable(this)
                .orElseThrow(IllegalStateException::new);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<AbstractIOData>> constraintViolations = validator.validate(this);

        if (constraintViolations.isEmpty()) return;

        ConstraintViolation<AbstractIOData> violation = constraintViolations.iterator().next();
        throw new IllegalStateException(
                violation.getPropertyPath() + " " + violation.getMessage()
        );
    }
}
