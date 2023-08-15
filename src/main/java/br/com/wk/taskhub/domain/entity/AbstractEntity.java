package br.com.wk.taskhub.domain.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Optional;
import java.util.Set;

public abstract class AbstractEntity {

    protected void validarDadosEntidade(AbstractEntity entity) {
        Optional.ofNullable(entity).orElseThrow(IllegalStateException::new);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<AbstractEntity>> constraintViolations = validator.validate(entity);

        if (constraintViolations.isEmpty()) return;

        ConstraintViolation<AbstractEntity> violation = constraintViolations.iterator().next();
        throw new IllegalStateException(
                violation.getPropertyPath() + " " + violation.getMessage()
        );
    }
}
