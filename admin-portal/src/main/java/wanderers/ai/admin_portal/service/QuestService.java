package wanderers.ai.admin_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.repository.QuestRepository;

import java.util.List;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    public void save(Quest quest) {
        questRepository.save(quest);
    }

    public Quest findById(Long id) {
        return questRepository.findById(id).orElse(null);
    }

    public void update(Long id, Quest updatedQuest) {
        Quest quest = findById(id);
        if (quest == null) return;
        quest.setName(updatedQuest.getName());
        questRepository.save(quest);
    }
}

