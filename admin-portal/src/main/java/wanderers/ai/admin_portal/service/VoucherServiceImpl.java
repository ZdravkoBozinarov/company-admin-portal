package wanderers.ai.admin_portal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderers.ai.admin_portal.model.Voucher;
import wanderers.ai.admin_portal.repository.VoucherRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository repository;

    public VoucherServiceImpl(VoucherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Voucher> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Voucher save(Voucher voucher) {
        // Optional: add business rules here (e.g. unique extId check)
        repository.findByExtId(voucher.getExtId()).ifPresent(existing -> {
            if (!existing.getId().equals(voucher.getId())) {
                throw new IllegalArgumentException("External ID already in use");
            }
        });
        return repository.save(voucher);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
