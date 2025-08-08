package wanderers.ai.admin_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.service.QuestService;


@Controller
public class QuestController {

    private final QuestService questService;

    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping("/")
    public String home() {
        return "overview";
    }

    @GetMapping("/quests")
    public String listQuests(Model model) {
        model.addAttribute("quests", questService.findAll());
        return "quests/list";
    }

    @GetMapping("/quests/create")
    public String showCreateForm(Model model) {
        model.addAttribute("quest", new Quest());
        return "quests/create";
    }

    @PostMapping("/quests/create")
    public String createQuest(@ModelAttribute Quest quest) {
        questService.save(quest);
        return "redirect:/quests";
    }

    @GetMapping("/quests/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Quest quest = questService.findById(id);
        model.addAttribute("quest", quest);
        return "quests/edit";
    }

    @PostMapping("/quests/edit/{id}")
    public String updateQuest(@PathVariable Long id, @ModelAttribute Quest quest) {
        questService.update(id, quest);
        return "redirect:/quests";
    }
}
