package wanderers.ai.admin_portal.api;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

public record QuestResponse(
        Long id,
        String name,
        String subtitle,
        String description,
        String bannerImage,
        String category,
        boolean enabled,
        boolean hidden,
        Integer checkCooldown,
        Integer maxCompletions,
        LocalDateTime createdAt,
        Instant notBefore,
        Instant expiry,
        String rrule,
        LocalDateTime dtstart,
        String template,
        Map<String,Object> data
) {}
