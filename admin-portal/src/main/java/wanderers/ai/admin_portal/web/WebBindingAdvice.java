package wanderers.ai.admin_portal.web;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class WebBindingAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        binder.registerCustomEditor(Instant.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) { setValue(null); return; }
                String s = text.trim();
                try { setValue(Instant.parse(s)); return; } catch (Exception ignore) {}

                try {
                    LocalDateTime ldt = LocalDateTime.parse(s);
                    setValue(ldt.atZone(ZoneId.systemDefault()).toInstant());
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid ISO-8601 datetime: '" + s + "'");
                }
            }

            @Override
            public String getAsText() {
                Instant v = (Instant) getValue();
                return v != null ? v.toString() : "";
            }
        });
    }
}
