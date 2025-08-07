package wanderers.ai.admin_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderers.ai.admin_portal.model.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
