package feedthemonster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FTMController {
	
	@Autowired
	MonstersDAO monstersDAO;
	
	@Autowired
	DropDownBoxValidator dropDownBoxValidator;
	
    @RequestMapping(value = "/")
    public ModelAndView startingPage(ModelMap model) {
    	model.addAttribute("choosenMonster", new Monster());
    	Monsters monsters = new Monsters();
    	monsters.setMonsters(monstersDAO.getMonsters());
		ModelAndView modelAndView = new ModelAndView("startingPage", "monstersList", monsters.getMonsters());
		modelAndView.addObject("monsterNames", monsters.monsterNames());
		return modelAndView;
    }
   
	@RequestMapping(value = "/feeding", method = RequestMethod.POST)
	public String feeding(@ModelAttribute("choosenMonster") @Validated Monster choosenMonster,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		
		dropDownBoxValidator.validate(choosenMonster, result);
		if (result.hasErrors()) {
	    	Monsters monsters = new Monsters();
	    	monsters.setMonsters(monstersDAO.getMonsters());
	    	model.addAttribute("monstersList", monsters.getMonsters());
	    	model.addAttribute("monsterNames", monsters.monsterNames());
			return "/startingPage";
		} else {
			Monster monster = monstersDAO.feedMonster(choosenMonster.getName());
			model.addAttribute("monster", monster);
			return "/feeding";
		}
	}
	
}
