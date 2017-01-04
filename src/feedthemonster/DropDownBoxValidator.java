package feedthemonster;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DropDownBoxValidator implements Validator {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
	   //just validate the Monster instances
	   return Monster.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Monster monster = (Monster) target;

		if("NONE".equals(monster.getName())) {
			errors.rejectValue("name", "required.name", "[Message wasn't found.] Please, select your monster.");
		}
	}
}
