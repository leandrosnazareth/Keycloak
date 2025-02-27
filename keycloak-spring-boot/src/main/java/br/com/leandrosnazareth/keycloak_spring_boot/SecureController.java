package br.com.leandrosnazareth.keycloak_spring_boot;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class SecureController {

    @GetMapping("/secure")
    public String securePage(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("name", principal.getFullName());
        return "secure";
    }
}