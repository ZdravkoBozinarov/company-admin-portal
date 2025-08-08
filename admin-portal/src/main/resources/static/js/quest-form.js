document.addEventListener("DOMContentLoaded", () => {
    const presetSelect = document.getElementById("templatePreset");
    const rruleInput = document.querySelector('[name="rrule"]');
    const dtstartWrapper = document.getElementById("dtstartWrapper");
    const notBeforeInput = document.querySelector('[name="not_before"]');

    // Smart preset logic
    if (presetSelect) {
        presetSelect.addEventListener("change", function () {
            const template = this.value;

            const dataField = document.querySelector('[name="data"]');
            const categoryField = document.querySelector('[name="category"]');
            const templateField = document.querySelector('[name="template"]');

            if (!dataField || !categoryField || !templateField) return;

            switch (template) {
                case "has_weapon":
                    templateField.value = "has_weapon";
                    dataField.value = JSON.stringify({ weapon: [7] });
                    categoryField.value = "gamer";
                    break;
                case "has_ram":
                    templateField.value = "has_ram";
                    dataField.value = JSON.stringify({ ram: 8 });
                    categoryField.value = "creator";
                    break;
                case "has_signal":
                    templateField.value = "has_signal";
                    dataField.value = JSON.stringify({ signal: true });
                    categoryField.value = "social";
                    break;
                case "passthrough":
                    templateField.value = "passthrough";
                    dataField.value = JSON.stringify({ type: "any" });
                    categoryField.value = "gamer";
                    break;
                default:
                    templateField.value = "";
                    dataField.value = "";
                    categoryField.value = "";
            }
        });
    }

    // Autofill not_before with current date-time
    if (notBeforeInput) {
        const now = new Date();
        now.setMinutes(now.getMinutes() - now.getTimezoneOffset()); // fix timezone
        notBeforeInput.value = now.toISOString().slice(0, 16);
    }

    // Show/hide dtstart dynamically
    function toggleDtstartVisibility() {
        if (!dtstartWrapper) return;
        dtstartWrapper.style.display = rruleInput.value.trim() ? "block" : "none";
    }

    if (rruleInput) {
        toggleDtstartVisibility(); // initial
        rruleInput.addEventListener("input", toggleDtstartVisibility);
    }
});

function updatePreview() {
    const val = (name) => document.querySelector(`[name="${name}"]`)?.value || "";
    const bool = (name) => document.querySelector(`[name="${name}"]`)?.checked;

    document.getElementById("previewName").textContent = val("name") || "Quest Name";
    document.getElementById("previewSubtitle").textContent = val("subtitle") || "Subtitle";
    document.getElementById("previewDescription").textContent = val("description") || "Description will appear here.";

    document.getElementById("previewCategory").textContent = val("category") || "-";
    document.getElementById("previewStatus").textContent = bool("enabled") ? "Enabled" : "Disabled";
    document.getElementById("previewHidden").textContent = bool("hidden") ? "Yes" : "No";
    document.getElementById("previewCooldown").textContent = val("check_cooldown") || "10";
    document.getElementById("previewMax").textContent = val("max_completition") || "1";
    document.getElementById("previewStart").textContent = formatDate(val("not_before")) || "-";
    document.getElementById("previewEnd").textContent = formatDate(val("expiry")) || "-";
    document.getElementById("previewRrule").textContent = val("rrule") || "-";
    document.getElementById("previewDtstart").textContent = formatDate(val("dtstart")) || "-";
    document.getElementById("previewTemplate").textContent = val("template") || "-";
    document.getElementById("previewData").textContent = val("data") || "{}";
}


function formatDate(datetimeLocal) {
    try {
        const date = new Date(datetimeLocal);
        return date.toLocaleString(undefined, {
            year: 'numeric', month: 'short', day: 'numeric',
            hour: '2-digit', minute: '2-digit'
        });
    } catch (e) {
        return datetimeLocal;
    }
}

document.addEventListener("DOMContentLoaded", () => {
    // ... (всичко от преди остава)

    // Live preview triggers
    const fields = ["name", "subtitle", "description", "template", "enabled", "not_before", "expiry"];
    fields.forEach(f => {
        const el = document.querySelector(`[name="${f}"]`);
        if (el) {
            el.addEventListener("input", updatePreview);
            el.addEventListener("change", updatePreview);
        }
    });

    updatePreview(); // Инициален рендер
});

