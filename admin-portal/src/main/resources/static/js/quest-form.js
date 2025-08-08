document.addEventListener("DOMContentLoaded", () => {
    const presetSelect = document.getElementById("templatePreset");
    const rruleInput = document.querySelector('[name="rrule"]');
    const dtstartWrapper = document.getElementById("dtstartWrapper");
    const notBeforeInput = document.querySelector('[name="not_before"]');

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

    // Smart Presets
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

            updatePreview(); // Обнови Live Preview веднага
        });
    }

    // Live Preview функция
    function updatePreview() {
        const get = id => document.getElementById(id)?.value || "-";

        const formatDateISO = val => {
            const date = new Date(val);
            return isNaN(date) ? "-" : date.toISOString();
        };

        const fallback = (val, placeholder) =>
            val && val.trim() !== "" && val !== "-" ? val : placeholder;


        document.getElementById("previewName").textContent = fallback(get("name"), "Quest Name");
        document.getElementById("previewSubtitle").textContent = fallback(get("subtitle"), "Quest Subtitle");
        document.getElementById("previewDescription").textContent = fallback(get("description"), "Quest Description");
        document.getElementById("previewCategory").textContent = get("category");
        document.getElementById("previewEnabled").textContent = document.getElementById("enabled")?.checked ? "Enabled" : "Disabled";
        document.getElementById("previewHidden").textContent = document.getElementById("hidden")?.checked ? "Yes" : "No";
        document.getElementById("previewCooldown").textContent = get("check_cooldown");
        document.getElementById("previewCompletions").textContent = get("max_completions");
        document.getElementById("previewStart").textContent = formatDateISO(get("not_before"));
        document.getElementById("previewEnd").textContent = formatDateISO(get("expiry"));
        document.getElementById("previewDtstart").textContent = formatDateISO(get("dtstart"));
        document.getElementById("previewRrule").textContent = get("rrule");
        document.getElementById("previewTemplate").textContent = get("template");

        let rawData = get("data");
        try {
          const data = JSON.parse(rawData);
          document.getElementById("previewData").textContent = JSON.stringify(data, null, 2);
        } catch (e) {
          document.getElementById("previewData").textContent = rawData.trim() === "" ? "{}" : "Invalid JSON";
        }
    }

    // Attach listeners
    [
        "name", "subtitle", "description", "category", "check_cooldown", "max_completition",
        "not_before", "expiry", "rrule", "dtstart", "template", "data"
    ].forEach(id => {
        const el = document.getElementById(id);
        if (el) {
            el.addEventListener("input", updatePreview);
            el.addEventListener("change", updatePreview);
        }
    });

    ["enabled", "hidden"].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.addEventListener("change", updatePreview);
    });

    updatePreview(); // Начално извикване
});
