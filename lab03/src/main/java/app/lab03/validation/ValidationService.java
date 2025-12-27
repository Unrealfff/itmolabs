package app.lab03.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Named
@ApplicationScoped
public class ValidationService implements Serializable {
    private final List<Integer> x = List.of(-4, -3, -2, -1, 0, 1, 2, 3, 4);

    public Float validateFloat(String x, String component) {
        try{
            if (x == null){
                throw new NumberFormatException();
            }
            return Float.parseFloat(x);
        }
        catch (NumberFormatException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, component + " is invalid", component + " coordinate must be a number!");
            throw new FacesException(message.toString());
        }
    }
}
