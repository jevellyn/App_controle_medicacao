package pi2.medTime.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;

    public Usuario(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(){
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Long getId() {return id;}
    public void setId(Long id){ this.id = id;}

    public Boolean validate(){
        //verificação de login
        if(getEmail().equals("adm@gmail.com") && getSenha().equals("123456")){
            return true;
        }
        return false;
    }
}
