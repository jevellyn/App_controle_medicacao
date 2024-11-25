package pi2.medTime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pi2.medTime.model.Medicamento;


/**
 * Interface `MedicamentoRepository` estende `JpaRepository`, que é uma interface do Spring Data JPA.
 * Esta interface fornece operações CRUD (Criar, Ler, Atualizar, Excluir) prontas para usar em relação à entidade `Medicamento`.
 *
 * O `MedicamentoRepository` gerencia a persistência e recuperação de objetos `Medicamento` no banco de dados.
 */
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByUsuarioId(Long usuarioId);

}