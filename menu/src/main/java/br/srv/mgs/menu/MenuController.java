package br.srv.mgs.menu;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MenuController {

    @GetMapping(value = { "/", "/index", "/index.html" })
    public String indexMain(Model model) {
        model.addAttribute("activeMenu", "dashboard");
        return "index";
    }

}
