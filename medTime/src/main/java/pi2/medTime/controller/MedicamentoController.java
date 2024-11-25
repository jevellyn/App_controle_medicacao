package pi2.medTime.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import pi2.medTime.model.Medicamento;
import pi2.medTime.model.Usuario;
import pi2.medTime.repository.MedicamentoRepository;
import pi2.medTime.repository.UsuarioRepository;
import pi2.medTime.service.TokenService;

@Controller
@RequestMapping("medicamento")
public class MedicamentoController {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @GetMapping("/listar")
    public ResponseEntity listarMedicamentos(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();

        String login = tokenService.validateToken(token);

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(login);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        ArrayList<Medicamento> Medicamentos = (ArrayList<Medicamento>) medicamentoRepository
                .findByUsuarioId(usuario.getId());
        // ArrayList<Medicamento> medicamentosPorUsuario = new ArrayList<>();

        // for (Medicamento m : todosMedicamenos) {
        //     if (m.getId() == usuario.getId()) {
        //         medicamentosPorUsuario.add(m);
        //     }
        // }

        return ResponseEntity.ok(Medicamentos);
    }

    // Rota de cadastro de medicamentos
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarMedicamento(@RequestBody Medicamento body,
            @RequestHeader("Authorization") String authorizationHeader) {
        // Extrair o token do cabeçalho
        String token = authorizationHeader.replace("Bearer ", "").trim();

        // Validar o token e obter o login do usuário
        String login = tokenService.validateToken(token);

        // Buscar o usuário no banco
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(login);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        // Associar o medicamento ao usuário
        body.setUsuario(usuario);

        // Salvar o medicamento
        this.medicamentoRepository.save(body);
        return ResponseEntity.ok("Medicamento cadastrado com sucesso");
    }

    // Rota de edição de medicamentos
    @PutMapping("/editar")
    public ResponseEntity editarMedicamento(@RequestBody Medicamento body,
            @RequestHeader("Authorization") String authorizationHeader) {
        // Extrair o token do cabeçalho
        String token = authorizationHeader.replace("Bearer ", "").trim();

        // Validar o token e obter o login do usuário
        String login = tokenService.validateToken(token);

        // Buscar o usuário no banco
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(login);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        // Verificar se o medicamento existe no banco e pertence ao usuário
        Medicamento medicamentoExistente = medicamentoRepository.findById(body.getId())
                .orElse(null);

        if (medicamentoExistente == null || !medicamentoExistente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(404).body("Medicamento não encontrado ou não pertence ao usuário");
        }

        // Atualizar os dados do medicamento existente
        medicamentoExistente.setNome(body.getNome());
        medicamentoExistente.setDescricao(body.getDescricao());
        medicamentoExistente.setDosagem(body.getDosagem());
        medicamentoExistente.setDuracao(body.getDuracao());
        medicamentoExistente.setFrequencia(body.getFrequencia());
        medicamentoExistente.setHorario(body.getHorario());

        // Salvar as alterações
        medicamentoRepository.save(medicamentoExistente);

        return ResponseEntity.ok("Medicamento atualizado com sucesso");
    }
}
