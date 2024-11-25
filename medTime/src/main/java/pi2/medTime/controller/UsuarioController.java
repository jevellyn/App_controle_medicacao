package pi2.medTime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pi2.medTime.model.Usuario;
import pi2.medTime.repository.UsuarioRepository;

import java.util.List;

/**
 * Controlador responsável por gerenciar operações relacionadas a usuários.
 * Fornece endpoints para listar usuários.
 */
@Controller
@RequestMapping("Usuario") // Define o prefixo "/Usuario" para todas as rotas deste controlador.
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository; // Repositório para interagir com os dados dos usuários.

    /**
     * Rota para listar todos os usuários cadastrados no banco de dados.
     *
     * @return ResponseEntity contendo a lista de usuários ou uma mensagem de erro.
     */
    @GetMapping("/listar")
    public ResponseEntity listarUsuarios() {
        // Recupera a lista de todos os usuários armazenados no repositório.
        List<Usuario> listaUsuarios = this.usuarioRepository.findAll();

        // Retorna a lista de usuários no corpo da resposta HTTP.
        return ResponseEntity.ok(listaUsuarios);
    }
}
