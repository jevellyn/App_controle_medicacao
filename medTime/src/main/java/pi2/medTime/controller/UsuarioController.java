package pi2.medTime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pi2.medTime.model.Usuario;
import pi2.medTime.repository.UsuarioRepository;

@Controller
@RequestMapping("Usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    // Rota para retornar todos os usuarios
    @GetMapping("/listar")
    public ResponseEntity listarUsuarios() {
        List<Usuario> listaUsuarios = this.usuarioRepository.findAll();

        return ResponseEntity.ok(listaUsuarios);

    }

}
