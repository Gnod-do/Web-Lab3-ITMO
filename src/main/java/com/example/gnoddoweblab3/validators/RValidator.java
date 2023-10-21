package com.example.gnoddoweblab3.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validateR")
public class RValidator implements Validator {
    private static final double min = 1;

    private static final double max = 3;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        double r = Double.parseDouble(String.valueOf(value));
        if (r < min || r > max) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null,"R must lie in the interval (1; 3)!"));
        }
    }
}
