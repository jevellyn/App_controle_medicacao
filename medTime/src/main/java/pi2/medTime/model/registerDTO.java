package pi2.medTime.model;

import pi2.medTime.Enum.UsuarioRole;

public record registerDTO(String email, String senha, UsuarioRole role, String nome) {
}
