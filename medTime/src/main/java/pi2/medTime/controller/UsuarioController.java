package pi2.medTime.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pi2.medTime.model.Usuario;
import pi2.medTime.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("cadastroDeUsuario")
    public String cadastroDeUsuario(){
        return "cadastroDeUsuario";
    }

    @PostMapping("/cadastrarUsuario")
    public String cadastrarUsuario(Model model, Usuario usuario){
        usuarioRepository.save(usuario);
        model.addAttribute("cadastroUsuarioSucesso", true);
        return "cadastroDeUsuario";


    }

}
