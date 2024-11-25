package pi2.medTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import pi2.medTime.model.Usuario;
/**
 * Interface `UsuarioRepository` estende `JpaRepository`, que é uma interface do Spring Data JPA.
 * Esta interface fornece operações CRUD (Criar, Ler, Atualizar, Excluir) prontas para usar em relação à entidade `Usuario`.
 *
 * O `UsuarioRepository` gerencia a persistência e recuperação de objetos `Usuario` no banco de dados.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
}

