package wanderers.ai.admin_portal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ext_id", nullable = false, unique = true)
    private String extId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String tag;

    @Column(name = "not_before")
    private LocalDateTime notBefore;

    @Column
    private LocalDateTime expiry;

    @Column(name = "max_uses")
    private Integer maxUses;

    public Voucher() {
    }

    public Voucher(String extId, String name, String description, String tag,
                   LocalDateTime notBefore, LocalDateTime expiry, Integer maxUses) {
        this.extId = extId;
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.notBefore = notBefore;
        this.expiry = expiry;
        this.maxUses = maxUses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDateTime getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(LocalDateTime notBefore) {
        this.notBefore = notBefore;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }

    public Integer getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(Integer maxUses) {
        this.maxUses = maxUses;
    }
}
