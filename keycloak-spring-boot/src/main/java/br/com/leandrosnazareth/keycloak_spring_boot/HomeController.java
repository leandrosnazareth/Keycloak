package br.com.leandrosnazareth.keycloak_spring_boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Redireciona para "src/main/resources/templates/index.html"
    }
    
    @GetMapping("/aplicacao1")
    public String aplicacao1() {
        //redirecionar para localhost:8082/
        return "redirect:http://localhost:8082/secure";
    }
    @GetMapping("/aplicacao2")
    public String aplicacao2() {
        //redirecionar para localhost:8082/
        return "redirect:http://localhost:8083/secure";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }
}
