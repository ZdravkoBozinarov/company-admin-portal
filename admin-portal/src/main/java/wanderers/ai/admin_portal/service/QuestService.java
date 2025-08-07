package wanderers.ai.admin_portal.service;

import org.springframework.stereotype.Service;
import wanderers.ai.admin_portal.model.Quest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestService {

    public List<Quest> getMockedQuests() {
        return List.of(
                new Quest(1L, "Connect X to Profile", "social", true, false, LocalDateTime.now().minusDays(1), null),
                new Quest(2L, "Bio Completer", "creator", true, false, LocalDateTime.now(), null),
                new Quest(3L, "Unlock Bow", "gamer", true, false, null, LocalDateTime.now().plusDays(5))
        );
    }
}
