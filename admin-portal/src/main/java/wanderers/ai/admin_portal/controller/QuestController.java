package wanderers.ai.admin_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuestController {

    @GetMapping("/quests")
    public String showQuests(Model model){
        List<Quest> quests = List.of
        new Quest(1L, "Победи 5 врага", "Kill", LocalDate.now(), LocalDate.now().plusDays(7), true),
                new Quest(2L, "Събери 10 XP", "Collect", LocalDate.now(), LocalDate.now().plusDays(14), false)
        );

        model.addAttribute("quests", quests);
        return "quests";
    }
}
