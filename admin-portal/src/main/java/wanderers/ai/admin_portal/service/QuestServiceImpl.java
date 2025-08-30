package wanderers.ai.admin_portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderers.ai.admin_portal.model.Quest;
import wanderers.ai.admin_portal.repository.QuestRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;

    @Override
    public List<Quest> getAll() {
        return questRepository.findAll();
    }

    @Override
    public Quest save(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    public Optional<Quest> findById(Long id) {
        return questRepository.findById(id);
    }
}
