package pi2.medTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pi2.medTime.model.Medicamento;
import pi2.medTime.repository.MedicamentoRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // Lista para armazenar notificações pendentes
    private final List<String> notificacoesPendentes = new ArrayList<>();

    // Executa a cada minuto
    @Scheduled(cron = "0 * * * * *")
    public void enviarNotificacoes() {
        int horarioAtual = LocalTime.now().getHour() * 100 + LocalTime.now().getMinute();
        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        for (Medicamento medicamento : medicamentos) {
            int horarioMedicamento = medicamento.getHorario().getHour() * 100 + medicamento.getHorario().getMinute();

            if (horarioMedicamento == horarioAtual) {
                String notificacao = "Lembrete: Tome " + medicamento.getDosagem() + " comprimido(s) de " + medicamento.getNome();
                notificacoesPendentes.add(notificacao); // Armazena a notificação para o frontend
            }
        }
    }

    // Método para obter notificações pendentes e limpar a lista
    public List<String> getNotificacoesPendentes() {
        List<String> notificacoes = new ArrayList<>(notificacoesPendentes);
        notificacoesPendentes.clear(); // Limpa a lista após o envio
        return notificacoes;
    }
}
