package wanderers.ai.admin_portal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.service.QuestService;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/quests")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("quests", questService.getAll());
        return "quests/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("quest", new Quest());
        return "quests/create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("quest") Quest quest,
                         BindingResult binding,
                         @RequestParam(value = "dataJson", required = false) String dataJson,
                         Model model) {

        if (dataJson != null && !dataJson.isBlank()) {
            try {
                var parsed = objectMapper.readValue(dataJson, new com.fasterxml.jackson.core.type.TypeReference<java.util.Map<String,Object>>(){});
                quest.setData(parsed);
            } catch (Exception ignored) {
                quest.setData(java.util.Map.of());
            }
        } else if (quest.getData() == null) {
            quest.setData(java.util.Map.of());
        }

        if (quest.getNotBefore() != null && quest.getExpiry() != null &&
                quest.getExpiry().isBefore(quest.getNotBefore())) {
            binding.rejectValue("expiry", "expiry.beforeNotBefore", "Expiry must be after Not Before");
        }

        if (binding.hasErrors()) {
            model.addAttribute("sdataJson", dataJson);
            return "quests/create";
        }

        questService.save(quest);
        return "redirect:/quests";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Quest quest = questService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quest Id:" + id));
        model.addAttribute("quest", quest);
        return "quests/view";
    }

    private Map<String, Object> safeParseMap(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (Exception ignored) {
            return Collections.emptyMap();
        }
    }

    private void normalize(Quest q) {
        if (q.getName() != null) q.setName(q.getName().trim());
        if (q.getCategory() != null) q.setCategory(q.getCategory().trim());
    }
}
