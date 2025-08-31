package wanderers.ai.admin_portal.api;

import jakarta.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

public record QuestRequest(
        @NotBlank @Size(max = 128) String name,
        @Size(max = 128) String subtitle,
        @Size(max = 1024) String description,
        @Size(max = 255) String bannerImage,
        @NotBlank @Size(max = 64) String category,
        Boolean enabled,
        Boolean hidden,
        @Min(0) Integer checkCooldown,
        @Min(0) Integer maxCompletions,
        Instant notBefore,
        Instant expiry,
        @Size(max = 256) String rrule,
        LocalDateTime dtstart,
        @Size(max = 128) String template,
        Map<String,Object> data
) {}
