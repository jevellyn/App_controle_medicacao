package pi2.medTime.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pi2.medTime.model.Usuario;
import pi2.medTime.repository.UsuarioRepository;

import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logar")
    public String logar(Model model, Usuario usuario) {
        if(validaLogin(usuario)){
            return "redirect:/home";
        };
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login";
    }

    public Boolean validaLogin(Usuario usuario){
        ArrayList<Usuario> todosUsuarios = (ArrayList<Usuario>) usuarioRepository.findAll();

        for(Usuario u : todosUsuarios){
            if(usuario.getEmail().equals(u.getEmail()) && usuario.getSenha().equals(u.getSenha()))
                return true;
        }
        return false;
    }



}
