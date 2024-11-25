package pi2.medTime.model;

import pi2.medTime.Enum.UsuarioRole;

import java.time.LocalDate;
/**
 * Classe `registerDTO` é um registro (record) que serve como um DTO (Data Transfer Object) para o registro de novos usuários.
 *
 * Essa classe encapsula os dados necessários para criar um novo usuário no sistema, fornecendo uma maneira simples e imutável de transportar informações entre as camadas da aplicação.
 */
public record registerDTO(String email, String senha, UsuarioRole role, String nome, LocalDate dataNascimento) {
}
