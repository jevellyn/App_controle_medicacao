package pi2.medTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi2.medTime.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
