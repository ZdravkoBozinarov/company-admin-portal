document.addEventListener("DOMContentLoaded", () => {
    const presetSelect = document.getElementById("templatePreset");
    if (!presetSelect) return;

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
});
