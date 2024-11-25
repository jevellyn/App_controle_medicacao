package pi2.medTime.model;

/**
 * DTO (Data Transfer Object) utilizado para encapsular os dados de autenticação de um usuário.
 *
 * Este objeto é usado para transferir informações de autenticação (e-mail e senha)
 *
 * @param email o e-mail do usuário.
 * @param senha a senha do usuário.
 */
public record AuthenticationDTO(String email, String senha) {
}
