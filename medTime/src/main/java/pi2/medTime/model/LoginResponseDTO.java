package pi2.medTime.model;

/**
 * DTO (Data Transfer Object) utilizado para encapsular a resposta do processo de autenticação.
 *
 * Este objeto é retornado ao cliente após a autenticação bem-sucedida,
 * contendo o token JWT (JSON Web Token) gerado.
 *
 * @param token o token JWT gerado para autenticação do usuário.
 */
public record LoginResponseDTO(String token) {
}
