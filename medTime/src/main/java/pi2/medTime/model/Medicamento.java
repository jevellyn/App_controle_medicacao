package pi2.medTime.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private int dosagem;//comprimidos
    private int duracao;//dias
    private int frequencia;//numero de vezes ao dia

    public Medicamento(Long id, String nome, String descricao, int dosagem, int duracao, int frequencia) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dosagem = dosagem;
        this.duracao = duracao;
        this.frequencia = frequencia;
    }

    public Medicamento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDosagem() {
        return dosagem;
    }

    public void setDosagem(int dosagem) {
        this.dosagem = dosagem;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }
}
