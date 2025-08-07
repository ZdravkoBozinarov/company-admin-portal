package wanderers.ai.admin_portal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Quest {
    private Long id;
    private String name;
    private String category;
    private boolean enabled;
    private boolean hidden;
    private LocalDateTime notBefore;
    private LocalDateTime expiry;

    public Quest(Long id, String name, String category, boolean enabled, boolean hidden, LocalDateTime notBefore, LocalDateTime expiry) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.enabled = enabled;
        this.hidden = hidden;
        this.notBefore = notBefore;
        this.expiry = expiry;
    }

}
