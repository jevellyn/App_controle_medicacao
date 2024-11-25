package pi2.medTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pi2.medTime.model.AuthenticationDTO;
import pi2.medTime.model.LoginResponseDTO;
import pi2.medTime.model.Usuario;
import pi2.medTime.model.registerDTO;
import pi2.medTime.repository.UsuarioRepository;
import pi2.medTime.service.TokenService;

/**
 * Classe responsável por gerenciar as operações de autenticação e registro de usuários.
 * Fornece endpoints para login e cadastro de novos usuários.
 */
@Controller
@RequestMapping("/auth") // Define o prefixo "/auth" para todas as rotas desta classe.
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager; // Gerencia o processo de autenticação.

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositório para interagir com a entidade Usuario no banco de dados.

    @Autowired
    private TokenService tokenService; // Serviço responsável por manipular tokens JWT.

    /**
     * Endpoint para autenticar um usuário.
     * Recebe um objeto AuthenticationDTO contendo e-mail e senha, valida as credenciais e retorna um token JWT.
     *
     * @param data Objeto com os dados de login (e-mail e senha).
     * @return ResponseEntity contendo o token JWT gerado ou erro em caso de falha.
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        // Cria um token de autenticação com os dados recebidos.
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        // Autentica o usuário utilizando o AuthenticationManager.
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Gera o token JWT para o usuário autenticado.
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        // Retorna o token JWT dentro de um objeto LoginResponseDTO.
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    /**
     * Endpoint para cadastrar um novo usuário.
     * Verifica se o e-mail já está registrado, encripta a senha e salva o novo usuário no banco de dados.
     *
     * @param data Objeto contendo os dados para o registro do usuário (nome, e-mail, senha, papel, data de nascimento).
     * @return ResponseEntity com status 200 OK em caso de sucesso ou 400 Bad Request se o e-mail já existir.
     */
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody registerDTO data) {
        // Verifica se o e-mail já está cadastrado no banco.
        if (this.usuarioRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build(); // Retorna erro 400 se o e-mail já estiver em uso.
        }

        // Encripta a senha antes de salvar no banco de dados.
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        // Cria um novo objeto Usuario com os dados fornecidos.
        Usuario newUser = new Usuario(data.email(), encryptedPassword, data.role(), data.nome(), data.dataNascimento());

        // Salva o novo usuário no banco de dados.
        this.usuarioRepository.save(newUser);

        // Retorna sucesso 200 OK.
        return ResponseEntity.ok().build();
    }
}
