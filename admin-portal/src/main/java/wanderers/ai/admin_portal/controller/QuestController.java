package wanderers.ai.admin_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wanderers.ai.admin_portal.model.Quest;

import java.time.LocalDate;
import java.util.List;

@Controller
public class QuestController {

    @GetMapping("/quests")
    public String showQuests(Model model) {
        List<Quest> quests = questService.getMockedQuests(); // или зареждане от JSON

        model.addAttribute("quests", quests);
        return "quests";
    }

}