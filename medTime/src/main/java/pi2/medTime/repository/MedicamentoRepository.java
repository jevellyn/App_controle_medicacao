package pi2.medTime.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pi2.medTime.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
