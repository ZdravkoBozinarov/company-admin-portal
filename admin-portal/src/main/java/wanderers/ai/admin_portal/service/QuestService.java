package wanderers.ai.admin_portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.repository.QuestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;

    public List<Quest> getAll() {
        return questRepository.findAll();
    }

    public Quest save(Quest quest) {
        return questRepository.save(quest);
    }
}
