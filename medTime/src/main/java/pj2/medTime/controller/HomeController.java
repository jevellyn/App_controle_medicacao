package pj2.medTime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pj2.medTime.model.Medicamento;

import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        Medicamento m = new Medicamento("Paracetamol", "Remédio para dor de cabeça", 1, 5, 1);
        medicamentos.add(m);
        mv.addObject("medicamentos", medicamentos);

        return mv;
    }

    public void puxaDoBanco() {

    }
}
