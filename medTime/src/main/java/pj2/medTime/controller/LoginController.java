package pj2.medTime.controller;


import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pj2.medTime.model.Usuario;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logar")
    public String logar(Model model, Usuario usuario) {
        if(usuario.validate()){
            return "redirect:/home";
        }
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login";
    }
}
