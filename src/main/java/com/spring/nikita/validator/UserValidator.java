package com.spring.nikita.validator;

import com.spring.nikita.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by nikita on 13.09.16.
 */
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;
        String firstName = user.getFirstName();

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "username.empty", "Username must not be empty111111.");
//
//        String firstName = user.getFirstName();
//        System.out.println(firstName);
//        if (user.getFirstName().getClass() != String.class) {
//            errors.rejectValue("firstName", "type.first.name");
//        }

        if (firstName.length() > 5) {
            errors.rejectValue("firstName", "first.name", "eeeeeee cho tam");
        }

    }
}
