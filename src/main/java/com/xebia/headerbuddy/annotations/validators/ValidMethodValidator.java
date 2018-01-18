package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.models.RequestBehaviour;
import org.eclipse.jgit.util.StringUtils;
import org.reflections.Reflections;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidMethodValidator implements ConstraintValidator<ValidMethod, String> {

    @Override
    public void initialize(ValidMethod constraintAnnotation) {
        // Left blank
    }

    /*
    * String value: Is a big string with the requested methods from the parameter the are divided by a ,
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<String> supportedMethods = getAllSupportedMethods();

        String[] methodsToPerform = value.split(",");

        for (String method : methodsToPerform) {
            //all is a method we support it means all the requests
            if (method.equals("all")) {
                break;
            }
            // If array contains a method not supported return false
            if (!supportedMethods.contains(StringUtils.toLowerCase(method))) {
                return false;
            }
        }
        // If no unsupported methods are found return true;
        return true;
    }

    private List<String> getAllSupportedMethods() {
        // Use reflection to see which classes inherit from RequestBehaviour
        // to find out which request methods are supported
        Reflections reflections = new Reflections("com.xebia.headerbuddy.models");
        Set<Class<? extends RequestBehaviour>> methodClasses = reflections.getSubTypesOf(RequestBehaviour.class);

        // Create list for method names
        List<String> methods = new ArrayList<>();

        for (Class<? extends RequestBehaviour> method : methodClasses) {
            // Get the name of the class
            String name = method.getSimpleName();

            // Remove 'Request' from the name
            name = name.substring(0, name.length() - 7);

            // Add the name to the list
            methods.add(StringUtils.toLowerCase(name));
        }

        return methods;
    }
}
