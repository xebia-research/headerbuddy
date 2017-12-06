package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.models.RequestBehaviour;
import org.reflections.Reflections;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidMethodValidator implements ConstraintValidator<ValidMethod, String> {

    @Override
    public void initialize(ValidMethod constraintAnnotation){
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){

        // Use reflection to see which classes inherit from RequestBehaviour
        // to find out which request methods are supported
        Reflections reflections = new Reflections("com.xebia.headerbuddy.models");
        Set<Class<? extends RequestBehaviour>> methodClasses = reflections.getSubTypesOf(RequestBehaviour.class);

        // Create list for method names
        List<String> methods = new ArrayList<>();

        for(Class<? extends RequestBehaviour> method : methodClasses){
            // Get the name of the class
            String name = method.getSimpleName();

            // Remove 'Request' from the name
            name = name.substring(0, name.length() - 7);

            // Add the name to the list
            methods.add(name.toLowerCase());
        }

        // Put all values in array
        String[] methodsToPerform = value.split(",");

        for(String method : methodsToPerform){
            // If array contains a method not supported return false
            if (!methods.contains(method.toLowerCase())){
                return false;
            }
        }
        // If no unsupported methods are found return true;
        return true;
    }
}
