document.addEventListener("DOMContentLoaded", () => {
  const links = document.querySelectorAll(".sidebar a");
  const currentPath = window.location.pathname;

  links.forEach(link => {
    if (link.getAttribute("href") === currentPath) {
      link.classList.add("active");
    }
    link.addEventListener("mousemove", e => {
      const rect = link.getBoundingClientRect();
      link.style.setProperty("--mx", `${e.clientX - rect.left}px`);
    });
  });
});
