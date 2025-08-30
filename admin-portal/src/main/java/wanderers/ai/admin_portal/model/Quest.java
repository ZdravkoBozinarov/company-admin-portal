package wanderers.ai.admin_portal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;

import java.time.Instant;
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

    @NotBlank @Size(max = 128)
    private String name;

    @Size(max = 128)
    private String subtitle;

    @Size(max = 1024)
    private String description;

    @Size(max = 255)
    private String bannerImage;

    @NotBlank
    @Size(max = 64)
    private String category;

    @Min(0)
    private Integer checkCooldown;

    @Min(0)
    private Integer maxCompletions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean enabled;

    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
    private Instant notBefore;

    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
    private Instant expiry;

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
