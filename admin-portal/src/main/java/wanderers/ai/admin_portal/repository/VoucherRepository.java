package wanderers.ai.admin_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderers.ai.admin_portal.model.Voucher;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Optional<Voucher> findByExtId(String extId);
}
