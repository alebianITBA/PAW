package ar.edu.itba.paw.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.itba.paw.forms.RegisterForm;

public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return RegisterForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"NotBlank.registerForm.confirmPassword");
		
		RegisterForm form = (RegisterForm)target;
		
		if(!(form.getPassword().equals(form.getConfirmPassword()))){
			errors.rejectValue("confirmPassword", "NotMatch.password");
		}
		
	}
	
}