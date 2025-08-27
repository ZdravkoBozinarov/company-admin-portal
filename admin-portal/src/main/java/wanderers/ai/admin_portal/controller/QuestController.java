package wanderers.ai.admin_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.repository.QuestRepository;
import wanderers.ai.admin_portal.service.QuestService;

import java.time.Instant;

@Controller
@RequestMapping("/quests")
public class QuestController {

    private final QuestRepository questRepository;

    public QuestController(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @GetMapping
    public String listQuests(Model model) {
        model.addAttribute("quests", questRepository.findAll());
        return "quests/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("quest", new Quest());
        return "quests/create";
    }

    @PostMapping
    public String saveQuest(@ModelAttribute Quest quest) {
        questRepository.save(quest);
        return "redirect:/quests";
    }

}

