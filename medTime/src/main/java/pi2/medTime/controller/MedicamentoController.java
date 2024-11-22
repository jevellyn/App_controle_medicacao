package pi2.medTime.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pi2.medTime.model.Medicamento;
import pi2.medTime.repository.MedicamentoRepository;

@Controller
@RequestMapping("medicamento")
public class MedicamentoController {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    //Rota para retornar todos os medicamentos
    @GetMapping("/listar")
    public ResponseEntity listarMedicamentos(){
        List<Medicamento> listaMedicamentos = this.medicamentoRepository.findAll();

        return ResponseEntity.ok(listaMedicamentos);

    }

    //Rota de cadastro de medicamentos
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarMedicamento(@RequestBody Medicamento body) {
        Medicamento novoMedicamento = new Medicamento(body.getId(),
                                                      body.getNome(),
                                                      body.getDescricao(),
                                                      body.getDosagem(),
                                                      body.getDuracao(),
                                                      body.getFrequencia(),
                                                      body.getHorario());


        this.medicamentoRepository.save(novoMedicamento);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/editar")
    public String editarMedicamento(@ModelAttribute("medicamento") Medicamento medicamento) {
        atualizar(medicamento);
        return "redirect:/home";
    }

    public Medicamento buscarPorId(Long id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado com o ID: " + id));
    }

    public void atualizar(Medicamento medicamento) {
        medicamentoRepository.save(medicamento);
    }

}
