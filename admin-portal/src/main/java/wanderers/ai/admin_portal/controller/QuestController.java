package wanderers.ai.admin_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.service.QuestService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class QuestController {

    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping("/")
    public String home() {
        return "overview";
    }

    @GetMapping("/quests")
    public String showQuests(Model model) {
        List<Quest> quests = questService.getMockedQuests(); // или зареждане от JSON

        model.addAttribute("quests", quests);
        return "quests";
    }

}