document.addEventListener("DOMContentLoaded", function () {
    const toggleLayout = document.getElementById("toggle-layout");
    const sidebar = document.getElementById("sidebar"); // Menu vertical
    const barSectionHorizontal = document.getElementById("bar-section-horizontal"); // Menu horizontal

    function configureButtons() {
        // Configurar tema claro/escuro (verificado em ambos os menus)
        const themeToggles = document.querySelectorAll("#toggle-dark, #toggle-dark-hori");
        themeToggles.forEach(themeToggle => {
            themeToggle.addEventListener("click", function () {
                console.log("Tema alternado!");
                // Lógica do tema (implemente sua lógica de troca de tema aqui)
            });
        });

        // Configurar alternância de layout
        const layoutToggle = document.getElementById("toggle-layout");
        if (layoutToggle) {
            layoutToggle.addEventListener("click", function () {
                console.log("Layout alternado!");
                toggleMenu();
            });
        }
    }

    function toggleMenu() {
        if (toggleLayout.checked) {
            // Exibir menu horizontal
            if (sidebar) sidebar.style.display = "none";
            if (barSectionHorizontal) barSectionHorizontal.style.display = "block";
        } else {
            // Exibir menu vertical
            if (barSectionHorizontal) barSectionHorizontal.style.display = "none";
            if (sidebar) sidebar.style.display = "block";
        }

        // Reconfigurar eventos
        configureButtons();
    }

    toggleLayout.addEventListener("change", toggleMenu);

    toggleMenu(); // Inicializar o estado

    configureButtons(); // Call configureButtons after DOMContentLoaded
});