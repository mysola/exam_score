package util;

import org.springframework.validation.BindingResult;

public class ValidatorUtil {
    public static void raiseFirstError(BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new IllegalArgumentException(bindingResult.getAllErrors().get(0).getCode());
        if(bindingResult.hasGlobalErrors())
            throw new IllegalArgumentException(bindingResult.getGlobalError().getCode());
    }
}
