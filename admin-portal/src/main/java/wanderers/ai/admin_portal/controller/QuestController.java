package wanderers.ai.admin_portal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.repository.QuestRepository;
import wanderers.ai.admin_portal.service.QuestService;

import java.time.Instant;
import java.util.Map;

@Controller
@RequestMapping("/quests")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    @GetMapping
    public String listQuests(Model model) {
        model.addAttribute("quests", questService.getAll());
        return "quests/list";
    }

    @GetMapping("/create")
    public String createQuestForm(Model model) {
        model.addAttribute("quest", new Quest());
        return "quests/create";
    }

    @PostMapping
    public String saveQuest(@ModelAttribute Quest quest,
                            @RequestParam(required = false, name="dataJson") String dataJson) {
        if (dataJson != null && !dataJson.isBlank()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Map<String,Object> parsed = mapper.readValue(dataJson, new TypeReference<>() {});
                quest.setData(parsed);
            } catch (Exception e) {
                quest.setData(Map.of()); // fallback empty JSON
            }
        }
        questService.save(quest);
        return "redirect:/quests";
    }
}

