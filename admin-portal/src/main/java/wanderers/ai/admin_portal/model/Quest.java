package wanderers.ai.admin_portal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quests")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subtitle;
    private String description;
    private String category;
    private boolean enabled;
    private boolean hidden;

    private LocalDateTime notBefore;
    private LocalDateTime expiry;

    private String template;

    @Column(columnDefinition = "TEXT")
    private String data;

    public Quest() {
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isHidden() {
        return hidden;
    }

    public LocalDateTime getNotBefore() {
        return notBefore;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
}
