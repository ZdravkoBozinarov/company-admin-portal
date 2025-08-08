package wanderers.ai.admin_portal.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wanderers.ai.admin_portal.model.Voucher;
import wanderers.ai.admin_portal.service.VoucherService;

import java.util.List;

@Controller
@RequestMapping("/vouchers")
public class VoucherController {

    private final VoucherService service;

    public VoucherController(VoucherService service) {
        this.service = service;
    }

    @GetMapping
    public String listVouchers(Model model) {
        List<Voucher> all = service.findAll();
        model.addAttribute("vouchers", all);
        return "vouchers/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("voucher", new Voucher());
        return "vouchers/create";
    }

    @PostMapping
    public String saveVoucher(@Valid @ModelAttribute("voucher") Voucher voucher,
                              BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "vouchers/create";
        }
        service.save(voucher);
        return "redirect:/vouchers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Voucher v = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid voucher Id:" + id));
        model.addAttribute("voucher", v);
        return "vouchers/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteVoucher(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/vouchers";
    }
}
