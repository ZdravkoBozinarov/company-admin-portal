package wanderers.ai.admin_portal.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.service.QuestService;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/quests")
@RequiredArgsConstructor
public class QuestApiController {

    private final QuestService questService;

    @GetMapping
    public List<QuestResponse> list(@RequestParam(required = false) String q) {
        return questService.getAll().stream()
                .filter(x -> q == null || q.isBlank() ||
                        contains(x.getName(), q) ||
                        contains(x.getDescription(), q) ||
                        contains(x.getCategory(), q))
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public QuestResponse get(@PathVariable Long id) {
        return questService.findById(id).map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Quest not found"));
    }

    @PostMapping
    public ResponseEntity<QuestResponse> create(@Valid @RequestBody QuestRequest req) {
        var entity = new Quest();
        apply(entity, req);
        var saved = questService.save(entity);
        return ResponseEntity.created(URI.create("/api/quests/" + saved.getId())).body(toResponse(saved));
    }

    private boolean contains(String s, String q) {
        return s != null && s.toLowerCase().contains(q.toLowerCase());
    }

    private void apply(Quest q, QuestRequest r) {
        q.setName(r.name() != null ? r.name().trim() : null);
        q.setSubtitle(r.subtitle());
        q.setDescription(r.description());
        q.setBannerImage(r.bannerImage());
        q.setCategory(r.category() != null ? r.category().trim() : null);
        q.setEnabled(Boolean.TRUE.equals(r.enabled()));
        q.setHidden(Boolean.TRUE.equals(r.hidden()));
        q.setCheckCooldown(r.checkCooldown());
        q.setMaxCompletions(r.maxCompletions());
        q.setNotBefore(r.notBefore());
        q.setExpiry(r.expiry());
        q.setRrule(r.rrule());
        q.setDtstart(r.dtstart());
        q.setTemplate(r.template());
        q.setData(Objects.requireNonNullElseGet(r.data(), java.util.Collections::emptyMap));
    }

    private QuestResponse toResponse(Quest q) {
        return new QuestResponse(
                q.getId(),
                q.getName(),
                q.getSubtitle(),
                q.getDescription(),
                q.getBannerImage(),
                q.getCategory(),
                Boolean.TRUE.equals(q.getEnabled()),
                Boolean.TRUE.equals(q.getHidden()),
                q.getCheckCooldown(),
                q.getMaxCompletions(),
                q.getCreatedAt(),
                q.getNotBefore(),
                q.getExpiry(),
                q.getRrule(),
                q.getDtstart(),
                q.getTemplate(),
                q.getData()
        );
    }
}
