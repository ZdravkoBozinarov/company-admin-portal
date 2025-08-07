package wanderers.ai.admin_portal.model;

import java.time.LocalDate;

public class Quest {
    public Long id;
    public String name;
    public String type;
    public LocalDate startDate;
    public LocalDate endDate;
    public boolean active;

    public Quest(Long id, String name, String type, LocalDate startDate, LocalDate endDate, boolean active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
    }

}
