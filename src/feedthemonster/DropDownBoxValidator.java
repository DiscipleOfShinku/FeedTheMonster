package feedthemonster;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DropDownBoxValidator implements Validator
{
    @SuppressWarnings("rawtypes")
    @Override
    public boolean supports(Class clazz)
    {
        // just validate the Select instances
        return Select.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Select select = (Select) target;

        if (select.getId() < 1)
        {
            errors.rejectValue("id", "required.id", "[Message wasn't found.] Please, select your monster.");
        }
    }
}
