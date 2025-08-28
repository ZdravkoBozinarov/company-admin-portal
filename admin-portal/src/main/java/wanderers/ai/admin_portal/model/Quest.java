package wanderers.ai.admin_portal.model;

import jakarta.persistence.*;
import lombok.*;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Entity
@Table(name = "quest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subtitle;
    private String description;
    private String bannerImage;
    private String category;
    private Boolean enabled;
    private Integer checkCooldown;
    private Integer maxCompletions;

    private LocalDateTime notBefore;
    private LocalDateTime expiry;
    private LocalDateTime createdAt;

    private String rrule;
    private LocalDateTime dtstart;
    private String template;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> data;

    private Boolean hidden;

    public String getFormattedCreatedAt() {
        return createdAt != null ? createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "—";
    }

}
