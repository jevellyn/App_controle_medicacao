package pj2.medTime.model;

public class Usuario {
    private String email;
    private String senha;

    public Usuario(String email, String senha) {
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

    public Boolean validate(){
        //verificação de login
        if(getEmail().equals("adm@gmail.com") && getSenha().equals("123456")){
            return true;
        }
        return false;
    }
}