package pi2.medTime.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pi2.medTime.model.Medicamento;
import pi2.medTime.model.Usuario;
import pi2.medTime.repository.MedicamentoRepository;
import pi2.medTime.repository.UsuarioRepository;
import pi2.medTime.service.TokenService;

/**
 * Controlador responsável por gerenciar operações relacionadas a medicamentos.
 * Fornece endpoints para listar, cadastrar e editar medicamentos, associando-os a usuários autenticados.
 */
@Controller
@RequestMapping("medicamento") // Define o prefixo "/medicamento" para todas as rotas deste controlador.
public class MedicamentoController {

    @Autowired
    MedicamentoRepository medicamentoRepository; // Repositório para interagir com os dados dos medicamentos.

    @Autowired
    UsuarioRepository usuarioRepository; // Repositório para interagir com os dados dos usuários.

    @Autowired
    TokenService tokenService; // Serviço responsável por validar tokens JWT e extrair informações do usuário.

    /**
     * Rota para listar todos os medicamentos associados ao usuário autenticado.
     *
     * @param authorizationHeader Cabeçalho da requisição contendo o token JWT.
     * @return ResponseEntity com a lista de medicamentos do usuário ou mensagem de erro.
     */
    @GetMapping("/listar")
    public ResponseEntity listarMedicamentos(@RequestHeader("Authorization") String authorizationHeader) {
        // Extrair o token do cabeçalho "Authorization".
        String token = authorizationHeader.replace("Bearer ", "").trim();

        // Validar o token e obter o login do usuário associado.
        String login = tokenService.validateToken(token);

        // Buscar o usuário no banco de dados com base no login.
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(login);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado"); // Retorna erro 404 se o usuário não for encontrado.
        }

        // Buscar todos os medicamentos do banco e filtrar pelos pertencentes ao usuário.
        ArrayList<Medicamento> todosMedicamenos = (ArrayList<Medicamento>) medicamentoRepository.findAll();
        ArrayList<Medicamento> medicamentosPorUsuario = new ArrayList<>();

        for (Medicamento m : todosMedicamenos) {
            // Verifica se o medicamento pertence ao usuário.
            if (m.getUsuario().getId().equals(usuario.getId())) {
                medicamentosPorUsuario.add(m);
            }
        }

        // Retorna a lista de medicamentos do usuário.
        return ResponseEntity.ok(medicamentosPorUsuario);
    }

    /**
     * Rota para cadastrar um novo medicamento para o usuário autenticado.
     *
     * @param body Objeto Medicamento com os dados do novo medicamento.
     * @param authorizationHeader Cabeçalho da requisição contendo o token JWT.
     * @return ResponseEntity com mensagem de sucesso ou erro.
     */
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarMedicamento(@RequestBody Medicamento body,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        // Extrair o token do cabeçalho "Authorization".
        String token = authorizationHeader.replace("Bearer ", "").trim();

        // Validar o token e obter o login do usuário associado.
        String login = tokenService.validateToken(token);

        // Buscar o usuário no banco de dados com base no login.
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(login);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado"); // Retorna erro 404 se o usuário não for encontrado.
        }

        // Associa o medicamento ao usuário autenticado.
        body.setUsuario(usuario);

        // Salva o medicamento no banco de dados.
        this.medicamentoRepository.save(body);
        return ResponseEntity.ok("Medicamento cadastrado com sucesso"); // Retorna mensagem de sucesso.
    }

    /**
     * Rota para editar um medicamento já existente associado ao usuário autenticado.
     *
     * @param body Objeto Medicamento com os dados atualizados.
     * @param authorizationHeader Cabeçalho da requisição contendo o token JWT.
     * @return ResponseEntity com mensagem de sucesso ou erro.
     */
    @PutMapping("/editar")
    public ResponseEntity editarMedicamento(@RequestBody Medicamento body,
                                            @RequestHeader("Authorization") String authorizationHeader) {
        // Extrair o token do cabeçalho "Authorization".
        String token = authorizationHeader.replace("Bearer ", "").trim();

        // Validar o token e obter o login do usuário associado.
        String login = tokenService.validateToken(token);

        // Buscar o usuário no banco de dados com base no login.
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(login);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado"); // Retorna erro 404 se o usuário não for encontrado.
        }

        // Verificar se o medicamento existe no banco e se pertence ao usuário autenticado.
        Medicamento medicamentoExistente = medicamentoRepository.findById(body.getId())
                .orElse(null);

        if (medicamentoExistente == null || !medicamentoExistente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(404).body("Medicamento não encontrado ou não pertence ao usuário");
        }

        // Atualiza os dados do medicamento com as informações recebidas.
        medicamentoExistente.setNome(body.getNome());
        medicamentoExistente.setDescricao(body.getDescricao());
        medicamentoExistente.setDosagem(body.getDosagem());
        medicamentoExistente.setDuracao(body.getDuracao());
        medicamentoExistente.setFrequencia(body.getFrequencia());
        medicamentoExistente.setHorario(body.getHorario());

        // Salva as alterações no banco de dados.
        medicamentoRepository.save(medicamentoExistente);

        return ResponseEntity.ok("Medicamento atualizado com sucesso"); // Retorna mensagem de sucesso.
    }
}
