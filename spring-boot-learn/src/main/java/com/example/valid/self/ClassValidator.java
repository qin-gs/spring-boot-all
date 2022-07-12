package com.example.valid.self;

import com.example.valid.group.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 类校验器
 */
public class ClassValidator implements ConstraintValidator<ClassValid, User> {

	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {
		// 根据需要判断 value 的每个参数
		return true;
	}
}
