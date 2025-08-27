package wanderers.ai.admin_portal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subtitle;
    private String description;
    private String bannerImage;
    private String category;
    private boolean enabled;
    private int checkCooldown;
    private int maxCompletions;
    private Instant createdAt;
    private Instant notBefore;
    private Instant expiry;
    private String rrule;
    private Instant dtstart;
    private String template;

    @Column(columnDefinition = "jsonb")
    private String data;

    private boolean hidden;
}

