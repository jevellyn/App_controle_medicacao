package pi2.medTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pi2.medTime.service.NotificacaoService;

import java.util.List;

@RestController
public class notificacoesController {
    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/api/notificacoes")
    public List<String> getNotificacoes() {
        return notificacaoService.getNotificacoesPendentes();
    }
}
