package com.example.valid;

import com.example.bean.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        User user = (User) target;
        if (user.getAge() < 0) {
            errors.rejectValue("age", "年龄需要大于0");
        } else if (user.getAge() > 100) {
            errors.rejectValue("age", "年龄需要小于100");
        }
    }
}
