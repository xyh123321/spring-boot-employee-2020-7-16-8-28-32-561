package com.thoughtworks.springbootemployee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderValidation, String> {

    String[] validString;

    @Override
    public void initialize(GenderValidation constraintAnnotation) {
        validString = constraintAnnotation.validValues();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       // validString
        return "male".equals(value) || "female".equals(value);
    }
}
