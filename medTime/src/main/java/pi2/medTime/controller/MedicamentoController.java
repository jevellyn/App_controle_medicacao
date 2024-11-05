package pi2.medTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pi2.medTime.model.Medicamento;
import pi2.medTime.repository.MedicamentoRepository;

import java.util.ArrayList;

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
    public String cadastrarMedicamento(){
        return "cadastroDeMedicamento";
    }

    @PostMapping("/cadastrarMedicamento")
    public String  CadastrarMedicamento(Model model, Medicamento medicamento){
        medicamentoRepository.save(medicamento);

        model.addAttribute("cadastroSucesso", true);
        return "cadastroDeMedicamento";
    }

}
