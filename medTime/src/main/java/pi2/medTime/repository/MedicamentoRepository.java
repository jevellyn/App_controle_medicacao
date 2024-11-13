package pi2.medTime.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pi2.medTime.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Medicamento m SET m.qnt_frequencia_restantes = (m.qnt_frequencia_restantes - 1) WHERE m.id = :id")
    void decrementQntFrequenciaRestantes(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Medicamento m SET m.duracao = m.duracao - 1 WHERE m.id = :id")
    void decrementDuracao(Long id);
}
