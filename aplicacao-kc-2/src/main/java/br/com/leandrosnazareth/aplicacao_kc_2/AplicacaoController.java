package br.com.leandrosnazareth.aplicacao_kc_2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AplicacaoController {

    @GetMapping("/")
    public String securePage() {
        return "redirect:/secure";
    }

    @GetMapping("/secure")
    public String secureEndpoint(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getFullName());
            model.addAttribute("email", principal.getEmail());
            model.addAttribute("givenName", principal.getGivenName());
            model.addAttribute("familyName", principal.getFamilyName());
            model.addAttribute("subject", principal.getSubject());
            model.addAttribute("claims", principal.getClaims());
        } else {
            model.addAttribute("name", "Guest");
        }
        return "index";
    }
}