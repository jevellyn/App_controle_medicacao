package pi2.medTime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataNascimento;
    private float peso;
    private float altura;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoAlergia alergia;

    public Boolean validate(){
        //verificação de login
        if(getEmail().equals("adm@gmail.com") && getSenha().equals("123456")){
            return true;
        }
        return false;
    }
}
