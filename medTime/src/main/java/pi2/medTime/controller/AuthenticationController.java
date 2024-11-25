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


@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
   private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    // @CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario ) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody registerDTO data){
        if(this.usuarioRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUser = new Usuario(data.email(), encryptedPassword, data.role(), data.nome(), data.dataNascimento());

        this.usuarioRepository.save(newUser);
        return ResponseEntity.ok().build();
    }







}
