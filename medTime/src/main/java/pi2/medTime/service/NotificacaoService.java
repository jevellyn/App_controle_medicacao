package pi2.medTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pi2.medTime.model.Medicamento;
import pi2.medTime.repository.MedicamentoRepository;

import java.time.LocalTime;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository; // Supondo que você tenha um repositório

    // Executa a cada minuto
    @Scheduled(cron = "0 * * * * *")
    public void enviarNotificacoes() {
        LocalTime agora = LocalTime.now();
        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getHorario().equals(agora)) {
                mostrarNotificacao(medicamento);
            }
        }
    }

    private void mostrarNotificacao(Medicamento medicamento) {
        // Código para notificar o usuário (ajuste conforme o tipo de notificação que deseja implementar)
        System.out.println("Lembrete: Tome " + medicamento.getDosagem() + " comprimido(s) de " + medicamento.getNome());
    }
}
