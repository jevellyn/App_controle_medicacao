package pi2.medTime.controller;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pi2.medTime.model.Medicamento;
import pi2.medTime.repository.MedicamentoRepository;

@Controller
public class MedicamentoController {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        ArrayList<Medicamento> medicamentos = (ArrayList<Medicamento>) medicamentoRepository.findAll();
        mv.addObject("medicamentos", medicamentos);
        return mv;
    }

    @GetMapping("/cadastroDeMedicamento")
    public String cadastrarMedicamento() {
        return "cadastroDeMedicamento";
    }

    @PostMapping("/cadastrarMedicamento")
    public String CadastrarMedicamento(Model model, Medicamento medicamento) {
        //regra para sempre iniciar o campo qnt_frequencia_restante igual o numero total de frequencia
        medicamento.setQnt_frequencia_restantes(medicamento.getFrequencia());
        medicamentoRepository.save(medicamento);

        model.addAttribute("cadastroMedicamentoSucesso", true);
        return "cadastroDeMedicamento";
    }

    @GetMapping("/editaMedicamento/{id}")
    public String getMedicamentoById(@PathVariable Long id, Model model) {
        Medicamento medicamento = buscarPorId(id);
        medicamento.setQnt_frequencia_restantes(medicamento.getFrequencia());
        model.addAttribute("medicamento", medicamento);
        return "editarMedicamento";
    }

    @PostMapping("/editarMedicamento")
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
