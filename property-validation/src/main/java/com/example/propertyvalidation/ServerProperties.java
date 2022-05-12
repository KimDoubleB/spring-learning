package com.example.propertyvalidation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "server")
@Validated
@Data
public class ServerProperties implements Validator {

    private int value1;
    private int value2;

    @Override
    public boolean supports(Class<?> clazz) {
        return ServerProperties.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "value1", "value1.empty");
        ValidationUtils.rejectIfEmpty(errors, "value2", "value2.empty");

        var properties = (ServerProperties) target;
        System.out.println("properties = " + properties);
        if (properties.getValue1() > properties.getValue2()) {
            errors.rejectValue("value1", "value1.greaterThanValue2", "value1 must be less than value2");
        }
    }

}
