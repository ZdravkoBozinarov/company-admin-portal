package wanderers.ai.admin_portal.service;

import wanderers.ai.admin_portal.model.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestService {
    List<Quest> getAll();
    Quest save(Quest quest);
    Optional<Quest> findById(Long id);
}
