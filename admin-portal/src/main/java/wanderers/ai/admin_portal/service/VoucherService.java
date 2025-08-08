package wanderers.ai.admin_portal.service;

import wanderers.ai.admin_portal.model.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherService {
    List<Voucher> findAll();

    Optional<Voucher> findById(Long id);

    Voucher save(Voucher voucher);

    void deleteById(Long id);
}
