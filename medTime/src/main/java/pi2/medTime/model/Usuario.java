package pi2.medTime.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pi2.medTime.Enum.TipoAlergia;
import pi2.medTime.Enum.UsuarioRole;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * A classe `Usuario` representa a entidade de um usuário no sistema.
 *
 * Essa classe implementa `UserDetails`, tornando-a compatível com o Spring Security
 * para gerenciar autenticação e autorização.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único para cada usuário no banco de dados.

    @Column(nullable = false)
    private String nome; // Nome completo do usuário.

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento; // Data de nascimento do usuário.

    private float peso; // Peso do usuário (opcional).
    private float altura; // Altura do usuário (opcional).

    @Column(nullable = false)
    private String email; // Endereço de e-mail do usuário (usado como nome de usuário no login).

    @Column(nullable = false)
    private String senha; // Senha do usuário.

    @Enumerated(EnumType.STRING)
    private TipoAlergia alergia; // Enum para o tipo de alergia do usuário (opcional).

    private UsuarioRole role; // tipo do usuário no sistema (ADMIN ou USER).

    // Construtor customizado usado para criar usuários no momento do registro.
    public Usuario(String email, String senha, UsuarioRole role, String nome, LocalDate dataNascimento) {
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Métodos necessários para implementação de `UserDetails` (Spring Security)
    // Como nem todos foram usados no projeto, alguns deles estão apenas retornando "TRUE", sem implementação especifica
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define as permissões do usuário com base no seu papel.
        if (this.role == UsuarioRole.ADMIN)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"), // Permissão de administrador.
                    new SimpleGrantedAuthority("ROLE_USER")   // Permissão básica de usuário.
            );
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER")); // Apenas permissão básica.
    }

    @Override
    public String getPassword() {
        return this.getSenha(); // Retorna a senha do usuário.
    }

    @Override
    public String getUsername() {
        return this.getEmail(); // O e-mail do usuário é usado como nome de login.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Define que a conta não está expirada.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Define que a conta não está bloqueada.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Define que as credenciais não estão expiradas.
    }

    @Override
    public boolean isEnabled() {
        return true; // Define que o usuário está ativo no sistema.
    }

    /**
     * Relacionamento bidirecional com a entidade `Medicamento`.
     *
     * - `mappedBy = "usuario"`: Indica que o campo `usuario` em `Medicamento` é o lado inverso.
     * - `cascade = CascadeType.ALL`: Altera medicamentos associados automaticamente quando o usuário é alterado.
     * - `orphanRemoval = true`: Remove medicamentos órfãos (não associados a nenhum usuário).
     * - `@JsonManagedReference`: Resolve problemas de serialização cíclica ao trabalhar com JSON.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Medicamento> medicamentos;

}
