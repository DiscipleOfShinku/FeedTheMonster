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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Controller
public class FTMController
{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("feedthemonster.jpa");

    EntityManager entityManager;

    @Autowired
    DropDownBoxValidator dropDownBoxValidator;

    @RequestMapping(value = "/")
    public ModelAndView startingPage(ModelMap model)
    {
        model.addAttribute("choosenMonster", new Monster());

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Monster> monsters = entityManager.createQuery("from Monster", Monster.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        ModelAndView modelAndView = new ModelAndView("startingPage", "monstersList", monsters);

        return modelAndView;
    }

    @RequestMapping(value = "/feeding", method = RequestMethod.POST)
    public String feeding(@ModelAttribute("choosenMonster") @Validated Monster choosenMonster,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes)
    {
        dropDownBoxValidator.validate(choosenMonster, result);
        if (result.hasErrors())
        {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Monster> monsters = entityManager.createQuery("from Monster", Monster.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();

            model.addAttribute("monstersList", monsters);

            return "/startingPage";

        } else
        {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Monster monster = entityManager.createQuery("from Monster where id = '" + choosenMonster.getId() + "'",
                                                        Monster.class)
                                           .getSingleResult();
            monster.incrementLevel();
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.close();

            model.addAttribute("monster", monster);

            return "/feeding";
        }
    }

}
