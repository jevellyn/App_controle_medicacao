package pi2.medTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi2.medTime.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
