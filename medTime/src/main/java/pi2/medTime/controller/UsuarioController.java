package pi2.medTime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pi2.medTime.Enum.TipoAlergia;
import pi2.medTime.model.Medicamento;
import pi2.medTime.model.Usuario;
import pi2.medTime.repository.UsuarioRepository;

import java.util.List;

@Controller
@RequestMapping("Usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    //Rota para retornar todos os usuarios
    @GetMapping("/listar")
    public ResponseEntity listarUsuarios(){
        List<Usuario> listaUsuarios = this.usuarioRepository.findAll();

        return ResponseEntity.ok(listaUsuarios);

    }



}
