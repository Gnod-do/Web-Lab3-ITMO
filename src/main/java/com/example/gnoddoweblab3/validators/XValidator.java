package com.example.gnoddoweblab3.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validateX")
public class XValidator implements Validator {

    private static final double min = -5;

    private static final double max = 5;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        double x = Double.parseDouble(String.valueOf(value));
        if (x < min || x > max) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "X must lie in the interval (-5; 5)!"));
        }
    }
}
